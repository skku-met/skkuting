package skkumet.skkuting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @OneToMany(mappedBy = "host")
    private List<Meetup> hostingAppointment = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount")
    private List<UserMeetupRel> joinedMeetupList = new ArrayList<>();

    public UserAccount(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }
}
