package skkumet.skkuting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skkumet.skkuting.domain.MeetupReview;

public interface MeetupReviewRepository extends JpaRepository<MeetupReview, Long> {
}
