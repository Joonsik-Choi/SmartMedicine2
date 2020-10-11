package kr.co.smrp.smrp.domain.medicine.regMedicine;
// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available


import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RegMedicineRepository extends JpaRepository<RegMedicine, Long> {
    ArrayList<RegMedicine> findAllByUserInfo(UserInfo userId);

    Optional<RegMedicine> findAllByUserInfoAndMedicineInfo(Optional<UserInfo> userInfo, MedicineInfo medicineInfo);
    Optional<RegMedicine> findAllByUserInfoAndMedicineInfoAndState(Optional<UserInfo> userInfo, MedicineInfo medicineInfo, BooleanType booleanType);
}