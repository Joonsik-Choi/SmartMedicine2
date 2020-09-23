package kr.co.smrp.smrp.dto.Message;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    ResultCode resultCode;
}
