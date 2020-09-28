package kr.co.smrp.smrp.dto.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineEffect;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import lombok.*;

import javax.persistence.Lob;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineEffectAskDto {
    private String itemSeq;
    private String effect;
    private String usageCapacity;
    private String precautions;

    public MedicineEffect toEntity(){
        return MedicineEffect.builder()
                                .effect(effect)
                                .precautions(precautions)
                                .usageCapacity(usageCapacity)
                                .build();
    }
}
