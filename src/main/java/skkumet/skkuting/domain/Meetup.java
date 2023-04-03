package skkumet.skkuting.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.constant.MeetupStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Entity
public class Meetup extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;
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
    @Builder.Default
    private Set<UserMeetupRel> userJoinedList = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "meetup", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetupReview> meetupReviewList = new LinkedHashSet<>();

    public static Meetup of(String title, String content, Integer max_member, Integer min_member,
            LocalDateTime due_date, LocalDateTime start_date, String duration, String place,
            AuthorizingPolicy authorizingPolicy, MeetupStatus meetupStatus, UserAccount host) {
        return new Meetup(null, title, content, max_member, min_member, due_date, start_date,
                duration, place, authorizingPolicy, meetupStatus, host, null, null);
    }

    public boolean isUserJoined(String userId) {
        return this.getUserJoinedList().stream().anyMatch(
            o -> o.isAllowed() && o.getUserAccount().getEmail() == userId
        );
    }

}
