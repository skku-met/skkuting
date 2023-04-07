package skkumet.skkuting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.MeetupReview;
import skkumet.skkuting.dto.CreateMeetupReviewInputDto;
import skkumet.skkuting.repository.MeetupRepository;
import skkumet.skkuting.repository.MeetupReviewRepository;
import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.errorcode.MeetupReviewErrorCode;

@Service
@Transactional
public class MeetupReviewService {
    @Autowired
    MeetupReviewRepository meetupReviewRepository;

    @Autowired
    MeetupRepository meetupRepository;

    public MeetupReview createReview(CreateMeetupReviewInputDto dto)
            throws DomainException {
        if (dto.reviewRecipientId() == dto.reviewSenderId()) {
            throw new DomainException(MeetupReviewErrorCode.SELF_REVIEW_NOT_ALLOWED);
        }

        Meetup meetup = meetupRepository.findById(dto.meetupId()).orElseThrow(
                () -> new DomainException(MeetupReviewErrorCode.MEETUP_NOT_EXIST));

        // 사용자 밋업 참가 여부 확인
        if (!meetup.isUserJoined(dto.reviewSenderId())) {
            throw new DomainException(MeetupReviewErrorCode.USER_NOT_JOINED_MEETUP);
        }

        // 리뷰 대상 밋업 참가 여부 확인
        if (!meetup.isUserJoined(dto.reviewRecipientId())) {
            throw new DomainException(MeetupReviewErrorCode.RECIPIENT_NOT_JOINED_MEETUP);
        }

        // 이미 리뷰가 작성되었는지 확인
        if (meetupReviewRepository.checkAlreadyCreated(dto.meetupId(), dto.reviewSenderId(),
                dto.reviewRecipientId())) {
            throw new DomainException(MeetupReviewErrorCode.ALREADY_CREATED_REVIEW);
        }

        return meetupReviewRepository.save(dto.toEntity());
    }
}
