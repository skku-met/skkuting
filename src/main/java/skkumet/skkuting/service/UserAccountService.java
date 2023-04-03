package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.repository.UserAccountRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;


    public UserAccount findById(String email) {
        return userAccountRepository.findById(email).get();
    }
}
