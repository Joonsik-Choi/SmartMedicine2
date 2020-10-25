package kr.co.smrp.smrp.dto.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.Gender;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.dto.medicine.Alarm.MedicineAlarmResponDto;
import kr.co.smrp.smrp.dto.medicine.info.SumMedInfo;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class UserDto {
    private String userId;
    private String email;
    private String name;
    private Gender gender;
    private LocalDate birth;
    private LocalDateTime createdAt;
    private List<SumMedInfo> sumMedInfos;
    private List<MedicineAlarmResponDto> medicineAlarms;

    public UserDto(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.email = userInfo.getEmail();
        this.name = userInfo.getName();
        this.gender = userInfo.getGender();
        this.birth = userInfo.getBirth();
        this.createdAt = userInfo.getCreatedAt();
        setList(userInfo.getMedicineAlarms(),userInfo.getRegMedicineList());
    }
    public void setList(List<MedicineAlarm> medicineAlarms, List<RegMedicine> regMedicines){
        List<MedicineAlarmResponDto> medicineAlarmResponDtos=new ArrayList<>();
        List<SumMedInfo> medicines=new ArrayList<>();
        if(medicineAlarms.size()!=0) {
            for (MedicineAlarm medicineAlarm : medicineAlarms) {
                medicineAlarmResponDtos.add(new MedicineAlarmResponDto(medicineAlarm));
            }
            this.medicineAlarms=medicineAlarmResponDtos;
        }
        if(regMedicines.size()!=0) {
            for (RegMedicine regMedicine : regMedicines) {
                medicines.add(new SumMedInfo(regMedicine));
            }
            this.sumMedInfos=medicines;
        }
    }

}
