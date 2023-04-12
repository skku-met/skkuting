package skkumet.skkuting.controller;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import skkumet.skkuting.dto.constant.AuthorizingPolicy;
import skkumet.skkuting.dto.request.CreateMeetupRequest;
import skkumet.skkuting.dto.response.CreateMeetupResponse;

@ActiveProfiles("testdb")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeetupControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate("alsdnr9999@naver.com", "1234");

    public String getBaseUri() {
        return "http://localhost:" + this.port;
    }

    @Test
    public void testCreateMeetup() {
        CreateMeetupRequest req = CreateMeetupRequest.builder()
                .title("title")
                .content("content")
                .startDate(LocalDateTime.now())
                .duration("1hour")
                .place("place")
                .authorizingPolicy(AuthorizingPolicy.AUTO)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateMeetupRequest> request = new HttpEntity<>(req, headers);

        ResponseEntity<CreateMeetupResponse> res = restTemplate.postForEntity(getBaseUri() + "/review", request,
                CreateMeetupResponse.class);

        Assertions.assertThat(res.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testCloseMeetup() {
        // TODO : 테스트용 Meetup 생성
        restTemplate.postForEntity(getBaseUri() + "/review" + 1 + "/close_recruit", null, String.class);
    }

}
