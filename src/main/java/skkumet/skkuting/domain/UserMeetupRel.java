package skkumet.skkuting.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserMeetupRel extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserAccount userAccount;

    @ManyToOne
    private Meetup meetup;

    private boolean isAllowed;

}
