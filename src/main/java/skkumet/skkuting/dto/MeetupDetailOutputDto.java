package skkumet.skkuting.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Builder;
import skkumet.skkuting.domain.Meetup;
import skkumet.skkuting.dto.constant.AuthorizingPolicy;

@Builder
public record MeetupDetailOutputDto(
		Long id,
		String title,
		Integer maxMember,
		Integer minMember,
		LocalDateTime startDate,
		String duration,
		String place,
		AuthorizingPolicy authorizingPolicy,
		String hostEmail,
		Set<String> userEmailList,
		LocalDateTime createdAt,
		LocalDateTime modifiedAt) {

	public static MeetupDetailOutputDto fromEntity(Meetup meetup) {
		return MeetupDetailOutputDto.builder()
				.id(meetup.getId())
				.title(meetup.getTitle())
				.maxMember(meetup.getMaxMember())
				.minMember(meetup.getMinMember())
				.startDate(meetup.getStartDate())
				.duration(meetup.getDuration())
				.place(meetup.getPlace())
				.authorizingPolicy(meetup.getAuthorizingPolicy())
				.hostEmail(meetup.getHost().getEmail())
				.userEmailList(
						meetup.getUserJoinedList().stream().map(
								o -> o.getUserAccount().getEmail())
								.collect(Collectors.toSet()))
				.createdAt(meetup.getCreatedAt())
				.modifiedAt(meetup.getModifiedAt())
				.build();
	}
}
