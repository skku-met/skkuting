package skkumet.skkuting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import skkumet.skkuting.domain.MeetupReview;

public interface MeetupReviewRepository extends JpaRepository<MeetupReview, Long> {
    @Query("select mr.id " +
            "from MeetupReview mr " +
            "where mr.meetup.id=:meetupId " +
            "and mr.reviewSender.id=:userId " +
            "and mr.reviewRecipient.id=:reviewSenderId")
    boolean checkAlreadyCreated(Long meetupId, String reviewSenderId, String reviewRecipientId);
}
