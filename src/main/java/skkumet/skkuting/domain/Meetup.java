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
import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.errorcode.MeetupErrorCode;

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
    private Integer maxMember;
    private Integer minMember;
    private LocalDateTime startDate;
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
            LocalDateTime start_date, String duration, String place,
            AuthorizingPolicy authorizingPolicy, MeetupStatus meetupStatus, UserAccount host) {
        return new Meetup(null, title, content, max_member, min_member, start_date,
                duration, place, authorizingPolicy, meetupStatus, host, null, null);
    }

    public static MeetupBuilder buildWithHost(UserAccount host) {
        UserMeetupRel hostRel = UserMeetupRel.builder().userAccount(host).isAllowed(true).build();
        Set<UserMeetupRel> initList = new LinkedHashSet<UserMeetupRel>();
        initList.add(hostRel);
        return Meetup.builder().host(host).userJoinedList(initList);
    }

    public void setHost(UserAccount host) {
        this.host = host;
        if (!this.userJoinedList.isEmpty()) {
            throw new RuntimeException("밋업 호스트는 최초 설정 이후 변경 불가능.");
        }
        this.userJoinedList.add(
                UserMeetupRel.builder().userAccount(host).isAllowed(true).build());
    }

    public boolean isUserJoined(String userId) {
        return this.getUserJoinedList().stream().anyMatch(
                o -> o.isAllowed() && o.getUserAccount().getEmail() == userId);
    }

    public void closeRecruitByHost(UserAccount host) {
        if (!this.getHost().equals(host)) {
            throw new DomainException(MeetupErrorCode.NOT_MEETUP_HOST);
        } else if (this.meetupStatus == MeetupStatus.OPEN) {
            this.meetupStatus = MeetupStatus.CLOSE;
        }
    }
}
