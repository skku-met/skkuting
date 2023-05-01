package skkumet.skkuting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserMeetupRel;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.UserMeetupRelDto;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.response.UserMeetupRelResponse;
import skkumet.skkuting.repository.MeetupRepository;
import skkumet.skkuting.repository.UserMeetupRelRepository;
import skkumet.skkuting.util.DomainException;
import skkumet.skkuting.util.errorcode.AccountErrorCode;
import skkumet.skkuting.util.errorcode.MeetupErrorCode;

import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserMeetupRelService {

    private final MeetupRepository meetupRepository;
    private final UserMeetupRelRepository userMeetupRelRepository;

    public ResponseEntity<UserMeetupRelResponse> join(UserAccountDto userAccountDto, Long meetupId) {
        Meetup meetup = meetupRepository.findById(meetupId)
                .orElseThrow(() -> new DomainException(MeetupErrorCode.MEETUP_NOT_EXIST));

        Set<UserMeetupRel> userMeetupRelSet = userMeetupRelRepository.findUserMeetupRelByMeetupIdAndAllowedIsTrue(meetupId);

        // 이미 존재하는 회원이면 가입 안된다.
        if(userMeetupRelSet.stream().anyMatch(rel -> rel.getUserAccount().getEmail().equals(userAccountDto.email()))){
            throw new DomainException(MeetupErrorCode.MEETUP_REL_ALREADY_EXIST);
        }

        if (userMeetupRelSet.size() < meetup.getMaxMember()) {
            boolean isallowed = !meetup.getAuthorizingPolicy().equals(AuthorizingPolicy.BY_HOST);
            UserMeetupRel entity = UserMeetupRel.builder()
                    .userAccount(userAccountDto.toEntity())
                    .meetup(meetup)
                    .allowed(isallowed)
                    .build();
            UserMeetupRel savedEntity = userMeetupRelRepository.save(entity);
            return ResponseEntity.status(200).body(UserMeetupRelResponse.from(savedEntity));
        }
        throw new DomainException(MeetupErrorCode.MEETUP_REL_SAVE_FAILED);

    }
    /*
        모임에 가입한 회원의 allowed 상태를 허락된 상태로 바꾼다.
     */
    public ResponseEntity<UserMeetupRelDto> acceptjoinedUser(UserAccountDto hostDto, Long meetupId, String joinUserEmail) {

        // 모임 찾아오기
        UserMeetupRel userMeetupRel = userMeetupRelRepository.findUserMeetupRelByMeetupIdAndUserAccountEmail(meetupId, joinUserEmail)
                .orElseThrow(() -> new DomainException(MeetupErrorCode.MEETUP_NOT_EXIST));

        // 모임과 호스트 비교
        if (!userMeetupRel.getUserAccount().getEmail().equals(hostDto.email())) {
            throw new DomainException(AccountErrorCode.NOT_A_HOST);
        }
        userMeetupRel.setAllowed(true);
        return ResponseEntity.status(200).body(UserMeetupRelDto.from(userMeetupRel));
    }

    public void cancelJoin(UserAccountDto userDto, Long meetupId, String cancelUserEmail){

        /*
        1. userDto가 host이거나
        2. userDto와 cancelUserEmail이 같다면
        3. meetupId와 cancelUserEmail 바탕으로 행 삭제
         */

        Meetup meetup = meetupRepository.findById(meetupId)
                .orElseThrow(() -> new DomainException(MeetupErrorCode.MEETUP_NOT_EXIST));

        if (userDto.email().equals(meetup.getHost().getEmail()) || userDto.email().equals(cancelUserEmail)) {
            UserMeetupRel userMeetupRel = userMeetupRelRepository.findUserMeetupRelByMeetupIdAndUserAccountEmail(meetupId, cancelUserEmail)
                    .orElseThrow(() -> new DomainException(MeetupErrorCode.MEETUP_NOT_EXIST));
            userMeetupRelRepository.delete(userMeetupRel);
        }
    }
}
