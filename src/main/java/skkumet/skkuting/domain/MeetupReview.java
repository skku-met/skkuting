package skkumet.skkuting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
@Builder
public class MeetupReview extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meetup_id")
    private Meetup meetup;

    @ManyToOne
    @JoinColumn(name = "review_recipient_id")
    private UserAccount reviewRecipient;

    @ManyToOne
    @JoinColumn(name = "review_sender_id")
    private UserAccount reviewSender;

    @Column(length = 1000)
    private String content;

    private Integer rating;
}
