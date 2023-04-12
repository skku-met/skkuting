package skkumet.skkuting.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserMeetupRel;
import skkumet.skkuting.repository.UserMeetupRelRepository;

@RequiredArgsConstructor
@Service
public class UserMeetupRelService {

    private final UserMeetupRelRepository repository;

    public UserMeetupRel createHostRel(Meetup meetup) {
        return repository
                .save(UserMeetupRel.builder().meetup(meetup).userAccount(meetup.getHost()).isAllowed(true).build());
    }

}
