package skkumet.skkuting.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.dto.request.SignupRequest;
import skkumet.skkuting.service.UserAccountService;
import skkumet.skkuting.util.ErrorCode;
import skkumet.skkuting.util.exception.User.UserSignupException;

@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (!signupRequest.isPasswordEqualToPasswordConfirm()) {
            throw new UserSignupException(ErrorCode.INCORRECT_PASSWORD_PASSWORDCONF);
        }
        userAccountService.signupUserAccount(signupRequest.toDto());
        return ResponseEntity.ok().body("ok");
    }

}
