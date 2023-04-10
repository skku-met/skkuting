package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.request.LoginRequest;
import skkumet.skkuting.repository.UserAccountRepository;
import skkumet.skkuting.util.JwtTokenProvider;
import skkumet.skkuting.util.TokenInfo;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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
        // TODO: 2023/04/09 이메일 존재여부 확인 및 이메일/비밀번호 같은지 확인

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        TokenInfo tokenInfo = jwtTokenProvider.createTokenObject(authenticationToken);
        return ResponseEntity.ok(tokenInfo);
    }
}
