package skkumet.skkuting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.CreateMeetupInputDto;
import skkumet.skkuting.dto.CreateMeetupOutputDto;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.repository.MeetupRepository;
import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.errorcode.MeetupErrorCode;

@RequiredArgsConstructor
@Transactional
@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;
    private final UserMeetupRelService meetupRelService;

    public CreateMeetupOutputDto createMeetup(CreateMeetupInputDto dto) {
        Meetup meetup = dto.toEntity();
        meetupRepository.save(meetup);
        meetupRelService.createHostRel(meetup);
        return CreateMeetupOutputDto.fromEntity(meetup);
    }

    public Page<MeetupDto> getMeetups(Pageable pageable) {
        return meetupRepository.findAll(pageable).map(MeetupDto::from);
    }

    public void closeRecruit(Long meetupId, UserAccount host) {
        Meetup meetup = meetupRepository.findById(meetupId).orElseThrow(
                () -> new DomainException(MeetupErrorCode.MEETUP_NOT_EXIST));

        meetup.closeRecruitByHost(host);
    }
}
