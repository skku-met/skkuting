package skkumet.skkuting.util;

import lombok.Builder;

@Builder
public record TokenInfo(
        String email,
        String accessCode,
        String refreshCode
) {

}
