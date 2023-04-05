package skkumet.skkuting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.util.ErrorCode;
import skkumet.skkuting.util.exception.User.UserSignupException;

@RestController
public class exController {

    @GetMapping("/ex")
    public void getException(){
        throw new UserSignupException(ErrorCode.DUPLICATE_LOGIN_ID);
    }
}
