package skkumet.skkuting.dto.request;

import skkumet.skkuting.dto.UserAccountDto;

public record SignupRequest(
        String email,
        String authCode,
        String password,
        String passwordConfirm,
        String nickname
) {
    public SignupRequest(String email, String authCode, String password, String passwordConfirm, String nickname) {
        this.email = email;
        this.authCode = authCode;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.nickname = nickname;
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                email,
                password,
                nickname
        );
    }
}
