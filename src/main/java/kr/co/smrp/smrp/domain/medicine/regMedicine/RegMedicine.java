package kr.co.smrp.smrp.domain.medicine.regMedicine;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class RegMedicine {
      @Id
      @GeneratedValue
      @Column(name = "REGISTER_ID")
    private java.lang.Long id;
      @Enumerated(javax.persistence.EnumType.STRING)
      @Column(name = "STATE")
    private BooleanType state;
      @ManyToOne
      @JoinColumn(name = "USER_INFO_ID")
    private UserInfo userInfo;
      @ManyToOne
      @JoinColumn(name = "MEDICINE_ID")
    private MedicineInfo medicineInfo;
      @OneToMany(mappedBy = "regMedicine")
    private List<AlarmRegMedicine> alarmRegMedicines;
    private LocalDateTime createdAt;

    public void finish() {
        this.state=BooleanType.FINISH;
    }
}