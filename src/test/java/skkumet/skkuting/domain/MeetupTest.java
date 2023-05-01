package skkumet.skkuting.domain;

import java.util.ArrayList;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import skkumet.skkuting.dto.constant.MeetupStatus;
import skkumet.skkuting.util.DomainException;

public class MeetupTest {
    private final UserAccount host = UserAccount.builder().email("host@meetup.com").build();
    private final UserAccount notHost = UserAccount.builder().email("notHost@meetup.com").build();

    Meetup.MeetupBuilder builderWithHost() {
        Set<UserMeetupRel> list = Set.of(
                UserMeetupRel.builder().userAccount(host).allowed(true).build());

        return Meetup.builder()
                .host(host)
                .userJoinedList(list);
    }

    @Test
    void testCreateMeetup() {
        Meetup meetup = builderWithHost().build();

        // 호스트는 반드시 모임에 참여한 유저여야 함
        Assertions.assertThat(
                meetup.getUserJoinedList().stream().map(
                        (o) -> o.getUserAccount()))
                .contains(host);
        Assertions.assertThat(
                meetup.getUserJoinedList().stream()
                        .anyMatch((o) -> o.getAllowed() & o.getUserAccount() == host));
    }

    @Test
    void testCloseRecruit() {
        // 열린 모임은 닫아야 함
        Meetup meetup = builderWithHost().meetupStatus(MeetupStatus.OPEN).build();
        meetup.closeRecruitByHost(host);

        Assertions.assertThat(meetup.getMeetupStatus()).isEqualTo(MeetupStatus.CLOSE);

        // 호스트가 아니면 닫을 수 없어야 함
        Assertions.assertThatThrownBy(() -> meetup.closeRecruitByHost(notHost)).isInstanceOf(DomainException.class);

        // 닫히거나 종료된 모임은 그대로여야 함
        ArrayList<MeetupStatus> list = new ArrayList<MeetupStatus>();
        list.add(MeetupStatus.CLOSE);
        list.add(MeetupStatus.END);

        // @formatter:off
        Assertions.assertThat(
            list.stream().map(
                    (o) -> builderWithHost().meetupStatus(o).build()
                ).allMatch(
                    (o) -> {
                        MeetupStatus origin = o.getMeetupStatus();
                        o.closeRecruitByHost(host);
                        return origin == o.getMeetupStatus();
                    }
                )).isTrue();
        // @formatter:on
    }
}
