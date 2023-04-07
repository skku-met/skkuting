package skkumet.skkuting.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("spring.jwt")
@Component
public class JwtTokenProvider {

    private String secretKey;
    private final long expiration = 1000L * 60 * 60; // 1시간


}
