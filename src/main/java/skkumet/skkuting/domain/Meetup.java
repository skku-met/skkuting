package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.*;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.constant.MeetupStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Entity
public class Meetup extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private MeetupStatus meetupStatus;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount host;

    @ToString.Exclude
    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY)
    private Set<UserMeetupRel> userJoinedList = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY)
    private Set<MeetupReview> meetupReviewList = new LinkedHashSet<>();

    public static Meetup of (String title,
                             String content,
                             Integer max_member,
                             Integer min_member,
                             LocalDateTime due_date,
                             LocalDateTime start_date,
                             String duration,
                             String place,
                             AuthorizingPolicy authorizingPolicy,
                             MeetupStatus meetupStatus,
                             UserAccount host) {
        return new Meetup(
                null,
                title,
                content,
                max_member,
                min_member,
                due_date,
                start_date,
                duration,
                place,
                authorizingPolicy,
                meetupStatus,
                host,
                null,
                null);
    }



}
