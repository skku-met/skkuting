package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Entity
public class UserAccount extends AuditingFields{

    @Id
    private String email;

    @Column(nullable = false) private String nickname;
    @Column(nullable = false) private String password;
    private Integer studentNumber;
    @Column(length = 1000) private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private Set<Meetup> hostingAppointment = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private Set<UserMeetupRel> joinedMeetupList = new LinkedHashSet<>();

    public static UserAccount of (String email, String nickname, String password, Integer studentNumber, String description) {
        return new UserAccount(email,nickname,password,studentNumber,description,null,null);
    }
}
