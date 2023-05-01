package skkumet.skkuting.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.domain.UserMeetupRel;
import skkumet.skkuting.dto.UserAccountDto;
import skkumet.skkuting.dto.UserMeetupRelDto;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.response.UserMeetupRelResponse;
import skkumet.skkuting.repository.MeetupRepository;
import skkumet.skkuting.repository.UserMeetupRelRepository;
import skkumet.skkuting.util.DomainException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMeetupRelServiceTest {

    @Mock private MeetupRepository meetupRepository;
    @Mock private UserMeetupRelRepository userMeetupRelRepository;
    @InjectMocks private UserMeetupRelService sut;

    @DisplayName("회원가입 성공시 200을 반환해야 한다.")
    @Test
    void join_shouldReturn200AndUserMeetupRelDto_whenUserAllowedToJoin() {
        // given
        Long meetupId = 1L;
        int maxMember = 10;
        String userEmail = "user@example.com";
        UserAccount userAccount = UserAccount.builder()
                .email(userEmail)
                .build();
        Meetup meetup = Meetup.builder()
                .id(meetupId)
                .host(userAccount)
                .maxMember(maxMember)
                .authorizingPolicy(AuthorizingPolicy.BY_HOST)
                .build();
        UserMeetupRel userMeetupRel = UserMeetupRel.builder()
                .meetup(meetup)
                .userAccount(userAccount)
                .build();
        when(meetupRepository.findById(meetupId)).thenReturn(Optional.of(meetup));
        when(userMeetupRelRepository.findUserMeetupRelByMeetupIdAndAllowedIsTrue(meetupId)).thenReturn(new HashSet<>());
        when(userMeetupRelRepository.save(any(UserMeetupRel.class))).thenReturn(userMeetupRel);

        // when
        ResponseEntity<UserMeetupRelResponse> response = sut.join(UserAccountDto.from(userAccount), meetupId);

        // then
        UserMeetupRelResponse body = response.getBody();
        assertNotNull(body);

    }
    @DisplayName("가입 인원이 다 차면 모임에 가입하지 못한다.")
    @Test
    void join_shouldReturnErrorwhenUserAllowedToJoin() {
        // given
        Long meetupId = 1L;
        int maxMember = -1;
        String userEmail = "user@example.com";
        UserAccount userAccount = UserAccount.builder()
                .email(userEmail)
                .build();
        Meetup meetup = Meetup.builder()
                .id(meetupId)
                .host(userAccount)
                .maxMember(maxMember)
                .authorizingPolicy(AuthorizingPolicy.BY_HOST)
                .build();

        when(meetupRepository.findById(meetupId)).thenReturn(Optional.of(meetup));
        when(userMeetupRelRepository.findUserMeetupRelByMeetupIdAndAllowedIsTrue(meetupId)).thenReturn(new HashSet<>());

        // when && then
        assertThrows(DomainException.class, () -> sut.join(UserAccountDto.from(userAccount), meetupId));
    }

    @DisplayName("가입이 허용이 되지 않은 회원은 가입할 수 없다.")
    @Test
    void notAllowedUserCantJoin ( ) {
        Long meetupId = 1L;
        int maxMember = 10;
        String userEmail = "user@example.com";
        UserAccount userAccount = UserAccount.builder()
                .email(userEmail)
                .build();
        Meetup meetup = Meetup.builder()
                .id(meetupId)
                .host(userAccount)
                .maxMember(maxMember)
                .authorizingPolicy(AuthorizingPolicy.BY_HOST)
                .build();
        UserMeetupRel userMeetupRel = UserMeetupRel.builder()
                .meetup(meetup)
                .userAccount(userAccount)
                .allowed(false)
                .build();

        when(userMeetupRelRepository.findUserMeetupRelByMeetupAndUserAccountEmail(meetupId,userEmail)).thenReturn(Optional.of(userMeetupRel));

        //when
        ResponseEntity<UserMeetupRelDto> response = sut.acceptjoinedUser(UserAccountDto.from(userAccount), meetupId, userEmail);

        //then
        UserMeetupRelDto body = response.getBody();
        assertThat(Objects.requireNonNull(body).allowed()).isTrue();

    }


}