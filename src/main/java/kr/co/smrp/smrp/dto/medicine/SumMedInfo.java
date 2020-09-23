package kr.co.smrp.smrp.dto.medicine;

import lombok.*;

import java.time.LocalDate;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SumMedInfo {
    private String imageUrl;
    private String itemSeq;
    private String itemName;
    private String entpName;
    private LocalDate createdAt;
}
