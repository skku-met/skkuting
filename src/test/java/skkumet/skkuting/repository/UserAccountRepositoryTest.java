package skkumet.skkuting.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import skkumet.skkuting.domain.UserAccount;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("JPA 연결 테스트")
@Import(TestJpaConfig.class)
@DataJpaTest
class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;


    @Test
    @DisplayName("select 테스트")
    void UserAccountRepository_select_test(){
        List<UserAccount> userAccountList = userAccountRepository.findAll();
        System.out.println(userAccountList);
        int size = userAccountList.size();
        assertThat(size).isEqualTo(userAccountRepository.count());
    }

    @Test
    @DisplayName("닉네임이 중복된 회원은 가입할 수 없다.")
    void UserAccountRepository_duplicated_nickname_test(){
        UserAccount user = UserAccount.of("test1@g.skku.edu", "nick1234", "1234", 17, "descr");
        UserAccount user2 = UserAccount.of("test2@g.skku.edu", "nick1234", "1234", 17, "descr");
        userAccountRepository.save(user);
        assertThrows(DataIntegrityViolationException.class, () -> {
            userAccountRepository.saveAndFlush(user2);
        });
    }

}