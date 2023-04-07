package skkumet.skkuting.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = jwtTokenProvider.resolveToken(req);
        Optional.ofNullable(token)
                .filter(jwtTokenProvider::validateTokenExpiration)
                .map(jwtTokenProvider::getAuthentication)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
    }
}
