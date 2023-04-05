package skkumet.skkuting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.dto.request.SignupRequest;
import skkumet.skkuting.service.UserAccountService;
import skkumet.skkuting.validator.SignupRequestValidator;

@RequiredArgsConstructor
@RestController
public class UserAccountController {

    private final SignupRequestValidator signupRequestValidator;
    private final UserAccountService userAccountService;
    @InitBinder("signupRequest")
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(signupRequestValidator);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Validated @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(404).body(bindingResult.toString());
        }
        userAccountService.signupUserAccount(signupRequest.toDto());
        return ResponseEntity.ok().body("ok");
    }

}
