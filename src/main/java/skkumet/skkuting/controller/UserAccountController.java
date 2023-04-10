package skkumet.skkuting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import skkumet.skkuting.dto.request.LoginRequest;
import skkumet.skkuting.dto.request.SignupRequest;
import skkumet.skkuting.service.UserAccountService;
import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.TokenInfo;
import skkumet.skkuting.util.errorcode.AccountErrorCode;

@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        if (!signupRequest.isPasswordEqualToPasswordConfirm()) {
            throw new DomainException(AccountErrorCode.INCORRECT_PASSWORD_PASSWORDCONF);
        }
        userAccountService.signupUserAccount(signupRequest.toDto());
        return ResponseEntity.ok().body("ok");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenInfo> login(@RequestBody LoginRequest loginRequest) {
        return userAccountService.loginMember(loginRequest);
    }

}
