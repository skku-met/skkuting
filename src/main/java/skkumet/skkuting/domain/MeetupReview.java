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
public class MeetupReview extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Meetup meetup;

    private String reviewRecipientId;

    @ManyToOne
    @JoinColumn(name = "reviewRecipientId")
    private UserAccount reviewRecipient;

    private String reviewSenderId;

    @ManyToOne
    @JoinColumn(name = "reviewSenderId")
    private UserAccount reviewSender;

    @Column(length = 1000)
    private String content;

    private Integer rating;
}
