package kr.co.smrp.smrp.dto.medicine.Alarm;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.DoseTime;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.alarmList.AlarmList;
import kr.co.smrp.smrp.dto.medicine.AlarmList.AlarmListDto;
import kr.co.smrp.smrp.dto.medicine.info.SumMedInfo;
import lombok.*;

import javax.persistence.Embedded;
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
    @Embedded
    private DoseTime doseTime;
    private String doseType;
    @Setter
    private List<AlarmListDto> alarmListList;


    public MedicineAlarmResponDto(MedicineAlarm medicineAlarm) {
        id=medicineAlarm.getId();
        userInfo=medicineAlarm.getUserInfo().getUserId();
        alarmName=medicineAlarm.getAlarmName();
        doseType=medicineAlarm.getDoseType();
        startAlarm=medicineAlarm.getStartAlarm();
        finishAlarm=medicineAlarm.getFinishAlarm();
        doseTime=medicineAlarm.getDoseTime();
        dosingPeriod=medicineAlarm.getDosingPeriod();
    }
}
