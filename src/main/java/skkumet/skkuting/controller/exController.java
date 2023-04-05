package skkumet.skkuting.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import skkumet.skkuting.util.ErrorCode;
import skkumet.skkuting.util.exception.User.UserDuplicatedException;

@RestController
public class exController {

    @GetMapping("/ex")
    public void getException(){
        throw new UserDuplicatedException(ErrorCode.DUPLICATE_LOGIN_ID);
    }
}
