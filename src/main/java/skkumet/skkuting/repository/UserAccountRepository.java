package skkumet.skkuting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import skkumet.skkuting.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
