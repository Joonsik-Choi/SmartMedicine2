package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.AlarmRegMedicineRepository;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarmRepository;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
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

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
@Service
public class MedicineAlarmService {
    private UserInfoRepository userInfoRepository;
    private MedicineAlarmRepository medicineAlarmRepository;
    private RegMedicineRepository regMedicineRepository;
    private AlarmRegMedicineRepository alarmRegMedicineRepository;

    public MedicineAlarmService(UserInfoRepository userInfoRepository,
                                MedicineAlarmRepository medicineAlarmRepository,
                                RegMedicineRepository regMedicineRepository,
                                AlarmRegMedicineRepository alarmRegMedicineRepository
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
    public void addMedicineAlarm(MedicineAlarmAskDto medicineAlarmAskDto) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(medicineAlarmAskDto.getUserId());
        MedicineAlarm medicineAlarm=MedicineAlarm.builder()
                                    .startAlarm(medicineAlarmAskDto.getStartAlarm())
                                    .finishAlarm(medicineAlarmAskDto.getFinishAlarm())
                                    .alarmName(medicineAlarmAskDto.getAlarmName())
                                    .doseType(medicineAlarmAskDto.getDoseType())
                                    .dosingPeriod(medicineAlarmAskDto.getDosingPeriod())
                                    .oneTimeCapacity(medicineAlarmAskDto.getOneTimeCapacity())
                                    .userInfo(userInfo.get())
                                    .build();
        medicineAlarmRepository.save(medicineAlarm);
        ArrayList<AlarmRegMedicine> alarmRegMedicines=new ArrayList<>();
        for(Long registreId: medicineAlarmAskDto.getRegisterId()){
            Optional<RegMedicine> regMedicine=regMedicineRepository.findById(registreId);
            AlarmRegMedicine alarmRegMedicine=AlarmRegMedicine.builder()
                                                .regMedicine(regMedicine.get())
                                                .medicineAlarm(medicineAlarm)
                                                .build();
            alarmRegMedicineRepository.save(alarmRegMedicine);
            alarmRegMedicines.add(alarmRegMedicine);
        }
        medicineAlarm.setAlarmRegMedicines(alarmRegMedicines);
        medicineAlarmRepository.save(medicineAlarm);
    }

    public Message deleteMedicineAlarm(Long medicineAlarmId) {
        Optional<MedicineAlarm> medicineAlarm=medicineAlarmRepository.findById(medicineAlarmId);
        for(AlarmRegMedicine alarmRegMedicine:medicineAlarm.get().getAlarmRegMedicines()){
            alarmRegMedicineRepository.delete(alarmRegMedicine);
        }
        medicineAlarmRepository.deleteById(medicineAlarmId);
        return Message.builder().resultCode(ResultCode.DELETE).build();
    }

    public MedicineAlarmResponDto getMedicineAlarm(Long medicineAlarmId) {
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
}
