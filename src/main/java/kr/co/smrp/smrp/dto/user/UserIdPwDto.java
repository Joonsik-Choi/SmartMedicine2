package kr.co.smrp.smrp.dto.user;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserIdPwDto {
    String userId;
    String passWord;
}
