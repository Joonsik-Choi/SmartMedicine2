package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;
import java.util.List;
import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineAlarm {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_ALARM_ID")
    private java.lang.Long id;
    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "medicineAlarm")
    private List<AlarmRegMedicine> alarmRegMedicines;
    private java.lang.String alarmName;
    private int dosingPeriod;
    private java.time.LocalDateTime startAlarm;
    private java.time.LocalDateTime finishAlarm;
    private int oneTimeCapacity;
    private java.lang.String doseType;

}