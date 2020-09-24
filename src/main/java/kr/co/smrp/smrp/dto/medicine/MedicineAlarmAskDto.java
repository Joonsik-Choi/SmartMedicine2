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
    private ArrayList<Long> registerId;
    private String alarmName;
    private int dosingPeriod;
    private LocalDateTime startAlarm;
    private LocalDateTime finishAlarm;
    private int oneTimeCapacity;
    private String doseType;
}
