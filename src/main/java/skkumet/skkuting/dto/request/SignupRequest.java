package skkumet.skkuting.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.MethodArgumentNotValidException;
import skkumet.skkuting.dto.UserAccountDto;

public record SignupRequest(

        @NotBlank
        @Email(message = "이메일 형식에 맞지 않습니다.")
        String email,
        String authCode,
        String password,
        String passwordConfirm,
        String nickname
) {
    public UserAccountDto toDto() {
        return UserAccountDto.of(
                email,
                nickname,
                password
        );
    }

    public boolean isPasswordEqualToPasswordConfirm() {
        return password.equals(passwordConfirm);
    }
}
