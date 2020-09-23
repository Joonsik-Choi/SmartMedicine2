package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarmRepository;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
@Service
public class MedicineAlarmService {
    private UserInfoRepository userInfoRepository;
    private MedicineAlarmRepository medicineAlarmRepository;

    public MedicineAlarmService(UserInfoRepository userInfoRepository, MedicineAlarmRepository medicineAlarmRepository){
    this.userInfoRepository=userInfoRepository;
    this.medicineAlarmRepository=medicineAlarmRepository;
}
    public List<MedicineAlarm> getMedicineAlarm(String userId) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(userId);
        List<MedicineAlarm> medicineAlarmList=medicineAlarmRepository.findByUserInfo(userInfo.get());
        return medicineAlarmList;
    }
}
