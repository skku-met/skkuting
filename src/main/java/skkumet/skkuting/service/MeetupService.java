package skkumet.skkuting.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.dto.MeetupDto;
import skkumet.skkuting.dto.request.MeetupRequest;
import skkumet.skkuting.repository.MeetupRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;
    private final UserAccountService userAccountService;

    public void saveMeetup(MeetupRequest request) {
        UserAccount userAccount = userAccountService.findById("test@g.skku.edu");
        meetupRepository.save(request.toDto(userAccount).toEntity());
    }

    public Page<MeetupDto> getMeetups(Pageable pageable) {
        return meetupRepository.findAll(pageable).map(MeetupDto::from);

    }
}
