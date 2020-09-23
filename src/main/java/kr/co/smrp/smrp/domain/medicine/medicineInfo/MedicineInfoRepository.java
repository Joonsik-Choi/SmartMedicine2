package kr.co.smrp.smrp.domain.medicine.medicineInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineInfoRepository extends JpaRepository<MedicineInfo,Long> {
    Optional<MedicineInfo> findByItemSeq(String medicineId);
}