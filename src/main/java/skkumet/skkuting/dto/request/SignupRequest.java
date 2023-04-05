package skkumet.skkuting.dto.request;

public record SignupRequest(
        String email,
        String authCode,
        String password,
        String passwordConfirm
) {
    public SignupRequest(String email, String authCode, String password, String passwordConfirm) {
        this.email = email;
        this.authCode = authCode;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
