package kr.co.smrp.smrp.domain.medicine.alarmList;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface AlarmListRepository extends JpaRepository<AlarmList, Long> {
    @Transactional
    void deleteAllByMedicineAlarm(MedicineAlarm medicineAlarm);
}
