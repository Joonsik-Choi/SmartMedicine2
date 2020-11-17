package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.AlarmRegMedicineRepository;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.DoseTime;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarmRepository;
import kr.co.smrp.smrp.domain.medicine.alarmList.AlarmList;
import kr.co.smrp.smrp.domain.medicine.alarmList.AlarmListRepository;
import kr.co.smrp.smrp.domain.medicine.alarmList.Bool;
import kr.co.smrp.smrp.domain.medicine.alarmRegMedicine.AlarmRegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicineRepository;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.Alarm.MedicineAlarmAskDto;
import kr.co.smrp.smrp.dto.medicine.Alarm.MedicineAlarmResponDto;
import kr.co.smrp.smrp.dto.medicine.AlarmList.AlarmListDto;
import kr.co.smrp.smrp.dto.medicine.info.SumMedInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
@Service
public class MedicineAlarmService {
    private UserInfoRepository userInfoRepository;
    private MedicineAlarmRepository medicineAlarmRepository;
    private RegMedicineRepository regMedicineRepository;
    private AlarmRegMedicineRepository alarmRegMedicineRepository;
    private AlarmListRepository alarmListRepository;

    public MedicineAlarmService(UserInfoRepository userInfoRepository, MedicineAlarmRepository medicineAlarmRepository,
                                RegMedicineRepository regMedicineRepository, AlarmRegMedicineRepository alarmRegMedicineRepository
                                , AlarmListRepository alarmListRepository){
    this.userInfoRepository=userInfoRepository;
    this.medicineAlarmRepository=medicineAlarmRepository;
    this.regMedicineRepository=regMedicineRepository;
    this.alarmRegMedicineRepository=alarmRegMedicineRepository;
    this.alarmListRepository=alarmListRepository;
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
    public MedicineAlarmResponDto  addMedicineAlarm(MedicineAlarmAskDto medicineAlarmAskDto) { //알람 등록
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(medicineAlarmAskDto.getUserId());
        MedicineAlarm medicineAlarm=medicineAlarmAskDto.toEntity();
        medicineAlarm.setUserInfo(userInfo.get());
        medicineAlarm.setStartAlarm(LocalDateTime.now());
        medicineAlarm.setFinishAlarm(LocalDateTime.now().plusDays(medicineAlarmAskDto.getDosingPeriod()));
        medicineAlarmRepository.save(medicineAlarm);
        System.out.println(medicineAlarm);
        ArrayList<AlarmRegMedicine> alarmRegMedicines=new ArrayList<>();
        addAlarmRegMedicine(medicineAlarmAskDto.getRegisterId(), medicineAlarm, alarmRegMedicines); //알람 약 관계 추가
        addAlarmList(medicineAlarm);
        medicineAlarmRepository.save(medicineAlarm);
        MedicineAlarmResponDto medicineAlarmResponDto=new MedicineAlarmResponDto(medicineAlarm);
        ArrayList<AlarmListDto> alarmListDtos=new ArrayList<>();
        for(AlarmList alarmList:medicineAlarm.getAlarmLists()){
            alarmListDtos.add(new AlarmListDto(alarmList));
        }
        medicineAlarmResponDto.setAlarmListList(alarmListDtos);
        return medicineAlarmResponDto;

    }

    public Message deleteMedicineAlarm(Long medicineAlarmId) {  //알람 삭제
        Optional<MedicineAlarm> medicineAlarm=medicineAlarmRepository.findById(medicineAlarmId);
        for(AlarmRegMedicine alarmRegMedicine:medicineAlarm.get().getAlarmRegMedicines()){
            alarmRegMedicineRepository.delete(alarmRegMedicine);
        }
        alarmListRepository.deleteAllByMedicineAlarm(medicineAlarm.get());
        medicineAlarmRepository.deleteById(medicineAlarmId);
        return Message.builder().resultCode(ResultCode.DELETE).build();
    }
    public MedicineAlarmResponDto getMedicineAlarm(Long medicineAlarmId) {  //단일 알람 출력
        MedicineAlarm medicineAlarm=medicineAlarmRepository.findById(medicineAlarmId).get();
        MedicineAlarmResponDto medicineAlarmResponDto=new MedicineAlarmResponDto(medicineAlarm);
        ArrayList<AlarmListDto> alarmListDtos=new ArrayList<>();
        for(AlarmList alarmList:medicineAlarm.getAlarmLists()){
            alarmListDtos.add(new AlarmListDto(alarmList));
        }
        medicineAlarmResponDto.setAlarmListList(alarmListDtos);
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

    public void addAlarmList(MedicineAlarm medicineAlarm){
        ArrayList<AlarmList> alarmListArrayLIst=new ArrayList<>();
        LocalTime currentTime=medicineAlarm.getStartAlarm().toLocalTime();
        LocalTime[] takeTimes=new LocalTime[]{LocalTime.of(7,30),LocalTime.of(12,30),LocalTime.of(19,10)};
        if(medicineAlarm.getDoseType().equals("식전")){
            for (int i = 0; i <3 ; i++) takeTimes[i]=takeTimes[i].minusMinutes(30); //식전
        }
        else{
            for (int i = 0; i <3 ; i++)  takeTimes[i]=takeTimes[i].plusMinutes(30); //식후
        }
        LocalDate localDate=LocalDate.now();
        int count=medicineAlarm.getDosingPeriod() *medicineAlarm.getDoseTime().totalDose();
        int idx=0;
        if(currentTime.getHour()>19){
            idx=0;
            localDate=localDate.plusDays(1);
        }
        else if(currentTime.getHour()>14){
            idx=2;
        }
        else if(currentTime.getHour()>9){
            idx=1;
        }
        else{
            idx=0;
        }
        DoseTime doseTime=medicineAlarm.getDoseTime();
        while(count>0){
            LocalDateTime localDateTime=LocalDateTime.of(localDate, takeTimes[idx]);
            AlarmList alarmList;
            if((doseTime.isMorning() && idx==0) ||(doseTime.isLunch() && idx==1) || (doseTime.isDinner() && idx==2)){
                alarmList=AlarmList.builder()
                        .medicineAlarm(medicineAlarm)
                        .time(localDateTime)
                        .checkDose(Bool.FF)
                        .build();
                alarmListRepository.save(alarmList);
                alarmListArrayLIst.add(alarmList);
                count--;
            }
            if((idx+1)%3==0){
                localDate=localDate.plusDays(1);
            }
            idx=(idx+1)%3;
        }
        medicineAlarm.setAlarmLists(alarmListArrayLIst);
    }
}
