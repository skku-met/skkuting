package skkumet.skkuting.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import skkumet.skkuting.dto.request.SignupRequest;
import skkumet.skkuting.repository.UserAccountRepository;
import skkumet.skkuting.service.EmailService;

@RequiredArgsConstructor
@Component
public class SignupRequestValidator implements Validator {

    private final EmailService emailService;
    private final UserAccountRepository userAccountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = (SignupRequest) target;

        //todo
        // @skku.edu 이메일인지 검증 로직이 필요함.

        checkUserDuplicate(request,errors);
        checkPasswordandPasswordConfirm(request,errors);
        checkEmailAuthCode(errors, request);

    }

    private void checkEmailAuthCode (Errors errors, SignupRequest request) {
        if (!emailService.validateEmailCode(request.email(), request.authCode())) {
            errors.rejectValue("authCode", "인증코드가 일치하지 않습니다.");
        }
    }


    private void checkUserDuplicate(SignupRequest request, Errors errors) {
        userAccountRepository.findById(request.email())
                .ifPresent(userAccount -> errors.rejectValue("email", "이미 가입된 회원이 존재합니다."));
    }

    private void checkPasswordandPasswordConfirm(SignupRequest request, Errors errors) {
        if (!request.password().equals(request.passwordConfirm())) {
            errors.rejectValue("password", "패스워드와 패스워드 확인이 일치하지 않습니다.");
        }
    }
}
