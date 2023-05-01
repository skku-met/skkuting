package skkumet.skkuting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skkumet.skkuting.domain.UserMeetupRel;

import java.util.Optional;
import java.util.Set;

public interface UserMeetupRelRepository extends JpaRepository<UserMeetupRel, Long> {
    Set<UserMeetupRel> findUserMeetupRelByMeetupIdAndAllowedIsTrue(Long meetupId);
    Optional<UserMeetupRel> findUserMeetupRelByMeetupIdAndUserAccountEmail(Long meetupId, String userEmail);
}
