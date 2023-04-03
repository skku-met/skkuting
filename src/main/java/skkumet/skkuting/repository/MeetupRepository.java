package skkumet.skkuting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import skkumet.skkuting.domain.Meetup;

public interface MeetupRepository extends JpaRepository<Meetup, Long> {
}
