package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.repository.UserAccountRepository;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;


}
