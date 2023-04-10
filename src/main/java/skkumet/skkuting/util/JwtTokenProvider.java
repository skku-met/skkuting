package skkumet.skkuting.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@ConfigurationProperties("spring.jwt")
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Getter @Setter private String secretKey;
    private final long accessTokenExpiration = 1000L * 60 * 60; // 1시간
    private final long millisecondsInDay = 24 * 60 * 60 * 1000;
    private final long refreshTokenExpiration = 14 * millisecondsInDay;
    private final UserDetailsService userDetailsService;

    public Key encodedSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public TokenInfo createTokenObject(Authentication authentication) {

        String accessToken = generateToken(authentication, accessTokenExpiration);
        String refreshToken = generateToken(authentication, refreshTokenExpiration);

        return TokenInfo.builder()
                .email(authentication.getName())
                .accessCode(accessToken)
                .refreshCode(refreshToken)
                .build();
    }

    public String generateToken(Authentication authentication, long expiration){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("auth", authorities);
        String w = Optional.of(Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(new Date().getTime() + expiration))
                        .signWith(encodedSecretKey(), SignatureAlgorithm.HS256)
                        .compact())
                        .orElseThrow();
        System.out.println(w);
        return w;
    }
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateTokenExpiration(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(encodedSecretKey()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e) {
            return false;
        }
    }

    public String getUserEmail(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(encodedSecretKey())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
    public Authentication getAuthentication(String token) {
        return Optional.ofNullable(token)
                .map(this::getUserEmail)
                .map(userDetailsService::loadUserByUsername)
                .map(userAccountPrincipal -> new UsernamePasswordAuthenticationToken(userAccountPrincipal, "", userAccountPrincipal.getAuthorities()))
                .orElseThrow(() -> new RuntimeException("토큰 인증에 실패하였습니다."));
    }
}
