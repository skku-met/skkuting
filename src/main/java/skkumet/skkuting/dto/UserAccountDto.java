package skkumet.skkuting.dto;

import skkumet.skkuting.domain.UserAccount;
import skkumet.skkuting.domain.UserMeetupRel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record UserAccountDto(
        String email,
        String nickname,
        String password,
        Integer studentNumber,
        String description,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {

    public static UserAccountDto of (String email,
                                     String nickname,
                                     String password,
                                     Integer studentNumber,
                                     String description,
                                     LocalDateTime createdAt,
                                     LocalDateTime modifiedAt,
                                     String createdBy,
                                     String modifiedBy) {
        return new UserAccountDto(
                email,
                nickname,
                password,
                studentNumber,
                description,
                createdAt,
                modifiedAt,
                createdBy,
                modifiedBy);
    }

    public static UserAccount toEntity(UserAccountDto dto) {
        return UserAccount.of(
                dto.email(),
                dto.nickname(),
                dto.password(),
                dto.studentNumber(),
                dto.description()
        );
    }

    public static UserAccountDto from(UserAccount entity) {
        return UserAccountDto.of(
                entity.getEmail(),
                entity.getNickname(),
                entity.getPassword(),
                entity.getStudentNumber(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy()
        );
    }
}