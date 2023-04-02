package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
public class MeetupReview {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meetup_id")
    private Meetup meetup;

    @ManyToOne
    private UserAccount review_from;

    @ManyToOne
    private UserAccount review_to;

    @Column(length = 1000) private String content;

    private Integer rating;
}
