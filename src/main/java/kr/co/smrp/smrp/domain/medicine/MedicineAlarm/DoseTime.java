package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class DoseTime {
    private String morning;
    private String lunch;
    private String dinner;
}
