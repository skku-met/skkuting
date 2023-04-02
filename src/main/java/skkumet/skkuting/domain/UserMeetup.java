package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserMeetup extends AuditingFields{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userAccount_email")
    private UserAccount userAccount;

    @ManyToOne
    private Meetup meetup;

    private boolean isAllowed;

}
