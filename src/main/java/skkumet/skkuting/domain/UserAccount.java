package skkumet.skkuting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
public class UserAccount {

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
    private List<UserMeetup> joinedMeetupList = new ArrayList<>();

}
