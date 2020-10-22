package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class DoseTime {
    @Enumerated(EnumType.STRING)
    private YesOrNo morning;
    @Enumerated(EnumType.STRING)
    private YesOrNo lunch;
    @Enumerated(EnumType.STRING)
    private YesOrNo dinner;
}
