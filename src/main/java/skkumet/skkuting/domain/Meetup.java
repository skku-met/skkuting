package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import skkumet.skkuting.dto.constant.AppStatus;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class Meetup extends AuditingFields{

    @Id
    @GeneratedValue
    private Long id;

    @Setter @Column(nullable = false) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;
    private Integer max_member;
    private Integer min_member;
    private LocalDateTime due_date;
    private LocalDateTime start_date;
    private String duration;
    private String place;

    @Enumerated(EnumType.STRING)
    private AuthorizingPolicy authorizingPolicy;

    @Enumerated(EnumType.STRING)
    private AppStatus appStatus;

    @ToString.Exclude
    @ManyToOne
    private UserAccount host;

    @ToString.Exclude
    @OneToMany(mappedBy = "meetup")
    private List<UserMeetup> userJoinedList = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "meetup")
    private List<MeetupReview> meetupReviewList = new ArrayList<>();


}
