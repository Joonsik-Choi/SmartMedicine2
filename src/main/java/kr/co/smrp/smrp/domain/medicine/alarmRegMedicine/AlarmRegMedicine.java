package kr.co.smrp.smrp.domain.medicine.alarmRegMedicine;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator.class)
public class AlarmRegMedicine {
    @Id
    @GeneratedValue
    @Column(name = "ALARM_REGISTER_MEDICINE_ID")
    private java.lang.Long id;
    @ManyToOne
    @JoinColumn(name = "MEDICINE_ALARM_ID")
    private MedicineAlarm medicineAlarm;
    @ManyToOne
    @JoinColumn(name = "REGISTER_ID")
    private RegMedicine regMedicine;
}