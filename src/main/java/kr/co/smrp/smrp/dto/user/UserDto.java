package kr.co.smrp.smrp.dto.user;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.user.userInfo.Gender;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String email;
    private String name;
    private Gender gender;
    private LocalDate birth;
    private LocalDateTime createdAt;
    private List<RegMedicine> regMedicineList;
    private List<MedicineAlarm> medicineAlarms;

    public UserDto(UserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.email = userInfo.getEmail();
        this.name = userInfo.getName();
        this.gender = userInfo.getGender();
        this.birth = userInfo.getBirth();
        this.createdAt = userInfo.getCreatedAt();
        this.regMedicineList = userInfo.getRegMedicineList();
        this.medicineAlarms = userInfo.getMedicineAlarms();
    }


}
