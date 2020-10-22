package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.AlarmRegMedicineRepository;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarmRepository;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicineRepository;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmAskDto;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmResponDto;
import kr.co.smrp.smrp.dto.medicine.SumMedInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
@Service
public class MedicineAlarmService {
    private UserInfoRepository userInfoRepository;
    private MedicineAlarmRepository medicineAlarmRepository;
    private RegMedicineRepository regMedicineRepository;
    private AlarmRegMedicineRepository alarmRegMedicineRepository;

    public MedicineAlarmService(UserInfoRepository userInfoRepository, MedicineAlarmRepository medicineAlarmRepository,
                                RegMedicineRepository regMedicineRepository, AlarmRegMedicineRepository alarmRegMedicineRepository
                                ){
    this.userInfoRepository=userInfoRepository;
    this.medicineAlarmRepository=medicineAlarmRepository;
    this.regMedicineRepository=regMedicineRepository;
    this.alarmRegMedicineRepository=alarmRegMedicineRepository;
}
    public List<MedicineAlarmResponDto> getMedicineAlarmAll(String userId) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(userId);
        List<MedicineAlarm> medicineAlarmList=medicineAlarmRepository.findByUserInfo(userInfo.get());
        List<MedicineAlarmResponDto> medicineAlarmResponDtos=new ArrayList<>();

        for(MedicineAlarm medicineAlarm: medicineAlarmList){
            MedicineAlarmResponDto medicineAlarmResponDto=new MedicineAlarmResponDto(medicineAlarm);
            ArrayList<SumMedInfo> regMedicines=new ArrayList<>();
            for(AlarmRegMedicine alarmRegMedicine: medicineAlarm.getAlarmRegMedicines()){
                Optional<RegMedicine> regMedicine=regMedicineRepository.findById(alarmRegMedicine.getRegMedicine().getId());
                regMedicines.add(new SumMedInfo(regMedicine.get()));
            }
            medicineAlarmResponDto.setRegMedicineArrayList(regMedicines);
            medicineAlarmResponDtos.add(medicineAlarmResponDto);
        }
        return medicineAlarmResponDtos;
    }
    public void addMedicineAlarm(MedicineAlarmAskDto medicineAlarmAskDto) { //알람 등록
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(medicineAlarmAskDto.getUserId());
        MedicineAlarm medicineAlarm=medicineAlarmAskDto.toEntity();
        medicineAlarm.setUserInfo(userInfo.get());
        medicineAlarm.setStartAlarm(LocalDateTime.now());
        medicineAlarm.setFinishAlarm(LocalDateTime.now().plusDays(medicineAlarmAskDto.getDosingPeriod()));
        medicineAlarmRepository.save(medicineAlarm);
        ArrayList<AlarmRegMedicine> alarmRegMedicines=new ArrayList<>();
        addAlarmRegMedicine(medicineAlarmAskDto.getRegisterId(), medicineAlarm, alarmRegMedicines); //알람 약 관계 추가
        medicineAlarmRepository.save(medicineAlarm);
    }

    public Message deleteMedicineAlarm(Long medicineAlarmId) {  //알람 삭제
        Optional<MedicineAlarm> medicineAlarm=medicineAlarmRepository.findById(medicineAlarmId);
        for(AlarmRegMedicine alarmRegMedicine:medicineAlarm.get().getAlarmRegMedicines()){
            alarmRegMedicineRepository.delete(alarmRegMedicine);
        }
        medicineAlarmRepository.deleteById(medicineAlarmId);
        return Message.builder().resultCode(ResultCode.DELETE).build();
    }
    public MedicineAlarmResponDto getMedicineAlarm(Long medicineAlarmId) {  //단일 알람 출력
        MedicineAlarm medicineAlarm=medicineAlarmRepository.findById(medicineAlarmId).get();
        MedicineAlarmResponDto medicineAlarmResponDto=new MedicineAlarmResponDto(medicineAlarm);
        ArrayList<SumMedInfo> regMedicines=new ArrayList<>();
        for(AlarmRegMedicine alarmRegMedicine: medicineAlarm.getAlarmRegMedicines()){
            Optional<RegMedicine> regMedicine=regMedicineRepository.findById(alarmRegMedicine.getRegMedicine().getId());
            regMedicines.add(new SumMedInfo(regMedicine.get()));
        }
        medicineAlarmResponDto.setRegMedicineArrayList(regMedicines);

        return medicineAlarmResponDto;
    }

    public void medicineAlarmUpdate(MedicineAlarmAskDto medicineAlarmAskDto) { // 알람 수정
        MedicineAlarm medicineAlarm=medicineAlarmRepository.findById(medicineAlarmAskDto.getId()).get();
        ArrayList<AlarmRegMedicine> alarmRegMedicines=new ArrayList<>();
        deleteAlarmRegMedicine(medicineAlarm); //regAlarmMedicine 삭제
        addAlarmRegMedicine(medicineAlarmAskDto.getRegisterId(), medicineAlarm, alarmRegMedicines); //regAlarmMedicine등록
        medicineAlarm.update(medicineAlarmAskDto);
        medicineAlarmRepository.save(medicineAlarm);
    }
public void deleteAlarmRegMedicine(MedicineAlarm medicineAlarm){
    for(AlarmRegMedicine alarmRegMedicine:medicineAlarm.getAlarmRegMedicines()){    //알람 -등록된 약  제거
        alarmRegMedicineRepository.delete(alarmRegMedicine);
    }
}
public void addAlarmRegMedicine(ArrayList<Long> registerId, MedicineAlarm medicineAlarm, ArrayList<AlarmRegMedicine> alarmRegMedicines){ //알람 약 관계 추가
    for(Long registreId: registerId) {     //수정된 약들 이름을 가져 와서 등록
        Optional<RegMedicine> regMedicine = regMedicineRepository.findById(registreId);
        AlarmRegMedicine alarmRegMedicine = AlarmRegMedicine.builder()
                .regMedicine(regMedicine.get())
                .medicineAlarm(medicineAlarm)
                .build();
        alarmRegMedicines.add(alarmRegMedicine);
        alarmRegMedicineRepository.save(alarmRegMedicine);
    }
    medicineAlarm.setAlarmRegMedicines(alarmRegMedicines);
    }
}
