package kr.co.smrp.smrp.dto.medicine;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegmedicineAskDto {
   String userId;
   String itemSeq;
}
