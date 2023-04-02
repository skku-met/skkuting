package skkumet.skkuting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skkumet.skkuting.domain.UserMeetup;

public interface UserMeetupRepository extends JpaRepository<UserMeetup, Long> {
}
