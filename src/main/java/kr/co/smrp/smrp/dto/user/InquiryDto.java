package kr.co.smrp.smrp.dto.user;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InquiryDto {
    private String userId;
    private String content;
}
