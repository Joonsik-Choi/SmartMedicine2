package kr.co.smrp.smrp.domain.medicine;

import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRegMedicineRepository extends JpaRepository<AlarmRegMedicine, Long> {
}
