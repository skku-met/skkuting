package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.request.LoginRequest;
import skkumet.skkuting.repository.UserAccountRepository;
import skkumet.skkuting.util.JwtTokenProvider;
import skkumet.skkuting.util.TokenInfo;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserAccount findById(String email) {
        return userAccountRepository.findById(email).get();
    }

    public void signupUserAccount(UserAccountDto dto) {
        UserAccount userAccount = UserAccount.of(
                dto.email(),
                dto.nickname(),
                passwordEncoder.encode(dto.password()),
                dto.studentNumber(),
                dto.description()
        );
        userAccountRepository.save(userAccount);
    }

    public ResponseEntity<TokenInfo> loginMember(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);
        TokenInfo tokenInfo = jwtTokenProvider.createTokenObject(authenticate);
        return ResponseEntity.ok(tokenInfo);
    }
}
