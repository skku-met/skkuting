package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class MeetupReview extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Meetup meetup;

    @ManyToOne
    private UserAccount review_from;

    @ManyToOne
    private UserAccount review_to;

    @Column(length = 1000) private String content;

    private Integer rating;
}
