package kr.co.smrp.smrp.domain.user.userInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.domain.user.Inquiry.Inquiry;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import lombok.*;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class UserInfo {
      @Id
      @GeneratedValue
      @Column(name = "USER_INFO_ID")
    private Long id;
    private String userId;
    private String userPw;
    private String email;
    private String name;

    @Enumerated(javax.persistence.EnumType.STRING)
    private Gender gender;

    private LocalDate birth;
    private LocalDateTime createdAt;
      @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY )
    private List<RegMedicine> regMedicineList;
      @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
    private List<MedicineAlarm> medicineAlarms;
      @OneToMany(mappedBy = "userInfo")
    private List<Inquiry> inquiries;

   
}