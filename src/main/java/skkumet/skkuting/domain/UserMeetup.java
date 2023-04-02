package skkumet.skkuting.domain;

import jakarta.persistence.*;

@Entity
public class UserMeetup {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userAccount_email")
    private UserAccount userAccount;

    @ManyToOne
    private Meetup meetup;

    private boolean isAllowed;

}
