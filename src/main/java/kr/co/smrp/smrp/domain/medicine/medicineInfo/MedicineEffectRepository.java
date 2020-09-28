package kr.co.smrp.smrp.domain.medicine.medicineInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface MedicineEffectRepository extends JpaRepository<MedicineEffect, Long> {
    Optional<MedicineEffect> findByEffect(MedicineEffect medicineEffect);
}