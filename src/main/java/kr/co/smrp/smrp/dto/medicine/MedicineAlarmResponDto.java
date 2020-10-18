package kr.co.smrp.smrp.dto.medicine;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineAlarmResponDto {
    private Long id;
    private String userInfo;
    @Setter
    private ArrayList<SumMedInfo> regMedicineArrayList;
    private String alarmName;
    private int dosingPeriod;
    private LocalDateTime startAlarm;
    private LocalDateTime finishAlarm;
    private int oneTimeCapacity;
    private String doseType;


    public MedicineAlarmResponDto(MedicineAlarm medicineAlarm) {
        id=medicineAlarm.getId();
        userInfo=medicineAlarm.getUserInfo().getUserId();
        alarmName=medicineAlarm.getAlarmName();
        doseType=medicineAlarm.getDoseType();
        startAlarm=medicineAlarm.getStartAlarm();
        finishAlarm=medicineAlarm.getFinishAlarm();
        oneTimeCapacity=medicineAlarm.getOneTimeCapacity();
        dosingPeriod=medicineAlarm.getDosingPeriod();
    }
}
