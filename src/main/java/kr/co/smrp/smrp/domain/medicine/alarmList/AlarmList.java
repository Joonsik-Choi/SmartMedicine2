package kr.co.smrp.smrp.domain.medicine.alarmList;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.regMedicine.BooleanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmList {
    @Id
    @GeneratedValue
    @Column(name = "ALARM_LIST_ID")
    Long id;
    LocalDateTime time;
    @Enumerated(EnumType.STRING)
    Bool checkDose;
    @ManyToOne
    @JoinColumn(name = "MEDICINE_ALARM_ID")
    MedicineAlarm medicineAlarm;
}
