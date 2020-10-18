package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmAskDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class MedicineAlarm {
    @Id
    @GeneratedValue
    @Column(name = "MEDICINE_ALARM_ID")
    private Long id;
    @Setter
    @ManyToOne
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "medicineAlarm")
    @Setter
    private List<AlarmRegMedicine> alarmRegMedicines;
    private String alarmName;
    private int dosingPeriod;
    @Setter
    private LocalDateTime startAlarm;
    @Setter
    private LocalDateTime finishAlarm;
    private int oneTimeCapacity;
    private String doseType;

}