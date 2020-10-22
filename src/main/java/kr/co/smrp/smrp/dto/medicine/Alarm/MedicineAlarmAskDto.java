package kr.co.smrp.smrp.dto.medicine.Alarm;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.DoseTime;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MedicineAlarmAskDto {
    private long id;
    private String userId;
    private ArrayList<Long> registerId;  //등록된 약 리스트
    private String alarmName; // 알람 이름
    private int dosingPeriod;  //복용기간
    private LocalDateTime startAlarm; //시작 시간
    private LocalDateTime finishAlarm; // 끝나는 시간
    @Embedded
    private DoseTime doseTime;
    private String doseType;  //복용 타입

    public MedicineAlarm toEntity(){
        return MedicineAlarm.builder()
                .startAlarm(this.getStartAlarm())
                .finishAlarm(this.getFinishAlarm())
                .alarmName(this.getAlarmName())
                .doseType(this.getDoseType())
                .dosingPeriod(this.getDosingPeriod())
                .doseTime(this.doseTime)
                .build();
    }
}
