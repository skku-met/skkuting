package skkumet.skkuting.domain;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import skkumet.skkuting.dto.constant.MeetupStatus;
import skkumet.skkuting.util.DomainException;

public class MeetupTest {
    private final UserAccount host = UserAccount.builder().email("host@meetup.com").build();
    private final UserAccount notHost = UserAccount.builder().email("notHost@meetup.com").build();

    @Test
    void testCreateMeetup() {
        Meetup meetup = Meetup.buildWithHost(host)
                .build();

        // 호스트는 반드시 모임에 참여한 유저여야 함
        Assertions.assertThat(
                meetup.getUserJoinedList().stream().map(
                        (o) -> o.getUserAccount()))
                .contains(host);
        Assertions.assertThat(
                meetup.getUserJoinedList().stream()
                        .anyMatch((o) -> o.isAllowed() & o.getUserAccount() == host));
    }

    @Test
    void testCloseRecruit() {
        // 열린 모임은 닫아야 함
        Meetup meetup = Meetup.buildWithHost(host).meetupStatus(MeetupStatus.OPEN).build();
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
                    (o) -> Meetup.buildWithHost(host).meetupStatus(o).build()
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
