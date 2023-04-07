package skkumet.skkuting.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Entity
public class UserAccount extends AuditingFields {

    @Id
    private String email;

    @Column
    private String nickname;
    @Column(nullable = false)
    private String password;
    private Integer studentNumber;
    @Column(length = 1000)
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Meetup> hostingAppointment = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserMeetupRel> joinedMeetupList = new LinkedHashSet<>();

    public static UserAccount of(String email, String nickname, String password,
            Integer studentNumber, String description) {
        return new UserAccount(email, nickname, password, studentNumber, description, null, null);
    }

    public static UserAccount of(String email) {
        return new UserAccount(email, null, null, null, null, null, null);
    }
}
