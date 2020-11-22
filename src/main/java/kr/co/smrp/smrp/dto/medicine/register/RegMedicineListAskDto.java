package kr.co.smrp.smrp.dto.medicine.register;

import lombok.*;

import java.util.ArrayList;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegMedicineListAskDto {
    String userId;
    ArrayList<String> itemSeq;
}
