package kr.co.smrp.smrp.dto.medicine.deep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MedicineDeepModelAskDto {
    String lineFront;
    String lineBack;
    String color;
    String printFront;
    String printBack;
    String drugShape;
}
