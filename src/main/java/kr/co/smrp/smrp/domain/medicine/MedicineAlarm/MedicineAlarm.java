package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.domain.medicine.alarmList.AlarmList;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.dto.medicine.Alarm.MedicineAlarmAskDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Setter
    @OneToMany(mappedBy = "medicineAlarm")
    private List<AlarmRegMedicine> alarmRegMedicines;
    private String alarmName;
    private int dosingPeriod;
    @Setter
    @OneToMany(mappedBy = "medicineAlarm") //알람용 클래스
    private List<AlarmList> alarmLists;

    @Setter
    private LocalDateTime startAlarm;
    @Setter
    private LocalDateTime finishAlarm;
    @Embedded
    private DoseTime doseTime;
    private String doseType;

    public void update(MedicineAlarmAskDto medicineAlarmAskDto){
        alarmName=medicineAlarmAskDto.getAlarmName();
        dosingPeriod=medicineAlarmAskDto.getDosingPeriod();
        finishAlarm=startAlarm.plusDays(medicineAlarmAskDto.getDosingPeriod());
        doseType=medicineAlarmAskDto.getDoseType();
       doseTime=medicineAlarmAskDto.getDoseTime();
    }
    public String toString(){
        return doseTime.getMorning().toString();
    }

}