package kr.co.smrp.smrp.dto.medicine;

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
    private String userId;
    private ArrayList<Long> registerId;  //등록된 약 리스트
    private String alarmName; // 알람 이름
    private int dosingPeriod;  //복용기간
    private LocalDateTime startAlarm; //시작 시간
    private LocalDateTime finishAlarm; // 끝나는 시간
    private int oneTimeCapacity;  //1회 복용 횟수
    private String doseType;  //복용 타입
}
