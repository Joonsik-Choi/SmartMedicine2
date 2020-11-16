package kr.co.smrp.smrp.dto.medicine.AlarmList;

import kr.co.smrp.smrp.domain.medicine.alarmList.AlarmList;
import kr.co.smrp.smrp.domain.medicine.alarmList.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmListDto {
    Long id;
    LocalDateTime time;
    Bool checkDose;

    public AlarmListDto(AlarmList alarmList){
        id=alarmList.getId();
        time=alarmList.getTime();
        checkDose=alarmList.getCheckDose();
    }
}
