package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skkumet.skkuting.controller.MeetupController;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.repository.MeetupRepository;

@RequiredArgsConstructor
@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;

    public void saveMeetup(MeetupDto meetupDto) {
        meetupRepository.save(meetupDto.toEntity());
    }
}
