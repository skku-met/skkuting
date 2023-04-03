package skkumet.skkuting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.dto.CreateMeetupReviewDto;
import skkumet.skkuting.repository.MeetupRepository;
import skkumet.skkuting.repository.MeetupReviewRepository;

@Service
@Transactional
public class MeetupReviewService {
    @Autowired
    MeetupReviewRepository meetupReviewRepository;

    @Autowired
    MeetupRepository meetupRepository;

    public CreateMeetupReviewDto.Success createReview(CreateMeetupReviewDto.Input dto) throws CreateMeetupReviewDto.Failed {

        Meetup meetup = meetupRepository.findById(dto.getMeetupId()).orElseThrow(
            () -> new CreateMeetupReviewDto.Failed("Meetup not found.")
        );

        // 사용자 밋업 참가 여부 확인
        if (!meetup.isUserJoined(dto.getReviewSenderId())) {
            throw new CreateMeetupReviewDto.Failed("User not joined meetup.");
        }

        // 리뷰 대상 밋업 참가 여부 확인
        if (!meetup.isUserJoined(dto.getReviewRecipientId())) {
            throw new CreateMeetupReviewDto.Failed("Recipient user not joined meetup.");
        }

        // 이미 리뷰가 작성되었는지 확인
        if (meetupReviewRepository.checkAlreadyCreated(dto.getMeetupId(), dto.getReviewSenderId(), dto.getReviewRecipientId())) {
            throw new CreateMeetupReviewDto.Failed("Already review created.");
        }

        return CreateMeetupReviewDto.Success.fromEntity(
            meetupReviewRepository.save(dto.toEntity())
        );
    }
}
