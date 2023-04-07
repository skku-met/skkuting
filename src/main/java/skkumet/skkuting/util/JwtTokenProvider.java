package skkumet.skkuting.util;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@ConfigurationProperties("spring.jwt")
@Component
public class JwtTokenProvider {

    private String secretKey;
    private final long expiration = 1000L * 60 * 60; // 1시간
    private final UserDetailsService userDetailsService;

    private String getSecretKey() {
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateTokenExpiration(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e) {
            return false;
        }
    }

    public String getUserEmail(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(getSecretKey())
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
