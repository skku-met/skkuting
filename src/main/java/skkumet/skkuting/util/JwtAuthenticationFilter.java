package skkumet.skkuting.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String accessToken = jwtTokenProvider.resolveToken(req);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(req);

        if (accessToken != null  && jwtTokenProvider.validateTokenExpiration(accessToken)) {
            if (refreshToken != null && jwtTokenProvider.validateTokenExpiration(refreshToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                TokenInfo tokenObject = jwtTokenProvider.createTokenObject(authentication);
                accessToken = tokenObject.accessCode();
                refreshToken = tokenObject.refreshCode();
            }
        }

        Optional<Authentication> authentication = Optional.ofNullable(accessToken)
                .filter(jwtTokenProvider::validateTokenExpiration)
                .map(jwtTokenProvider::getAuthentication);
        authentication.ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        chain.doFilter(request,response);
    }
}
