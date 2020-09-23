package kr.co.smrp.smrp.domain.medicine.MedicineAlarm;

import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MedicineAlarmRepository extends JpaRepository<MedicineAlarm, Long> {
    List<MedicineAlarm> findByUserInfo(UserInfo userInfo);
}
