package kr.co.smrp.smrp.dto.user;

import kr.co.smrp.smrp.domain.user.userInfo.Gender;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinUserAskDto {
    private String id;
    private String email;
    private String password;
    private String birth;
    private String name;
    private Gender gender;

    public UserInfo toEntity() {
        return UserInfo.builder()
                        .userId(id)
                        .email(email)
                        .userPw(password)
                        .birth(LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd")))
                        .name(name)
                        .gender(gender)
                        .createdAt(LocalDateTime.now())
                        .build();
    }
}
