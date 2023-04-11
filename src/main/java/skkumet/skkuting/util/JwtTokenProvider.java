package skkumet.skkuting.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ConfigurationProperties("spring.jwt")
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Getter @Setter private String secretKey;
    private final long accessTokenExpiration = 1000L * 60 * 60; // 1시간
    private final long millisecondsInDay = 24 * 60 * 60 * 1000;
    private final long refreshTokenExpiration = 14 * millisecondsInDay;

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
        return Optional.of(Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(new Date().getTime() + expiration))
                        .signWith(encodedSecretKey(), SignatureAlgorithm.HS256)
                        .compact())
                        .orElseThrow();
    }
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-REFRESH-TOKEN");
    }

    public boolean validateTokenExpiration(String token) {
        try {
            Jws<Claims> claims = Jwts
                    .parserBuilder()
                            .setSigningKey(encodedSecretKey())
                            .build()
                            .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public String getUserEmail(String token) {
        try {
            return Jwts.
                    parserBuilder()
                    .setSigningKey(encodedSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new).toList();
        return new UsernamePasswordAuthenticationToken(claims.getSubject(),"", authorities);
    }
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(encodedSecretKey()).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
