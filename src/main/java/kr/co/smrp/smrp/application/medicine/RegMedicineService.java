package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.domain.medicine.regMedicine.BooleanType;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicine;
import kr.co.smrp.smrp.domain.medicine.regMedicine.RegMedicineRepository;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.register.RegMedicineListAskDto;
import kr.co.smrp.smrp.dto.medicine.register.RegmedicineAskDto;
import kr.co.smrp.smrp.dto.medicine.info.SumMedInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegMedicineService {
    private RegMedicineRepository regMedicineRepository;
    private MedicineInfoRepository medicineInfoRepository;
    private UserInfoRepository userInfoRepository;

    RegMedicineService(RegMedicineRepository regMedicineRepository,MedicineInfoRepository medicineInfoRepository, UserInfoRepository userInfoRepository) {
            this.regMedicineRepository=regMedicineRepository;
            this.medicineInfoRepository=medicineInfoRepository;
            this.userInfoRepository=userInfoRepository;
    }
    public void addRegMedicineList(RegMedicineListAskDto regMedicineListAskDto) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(regMedicineListAskDto.getUserId());
        if (regMedicineListAskDto.getItemSeq().size() != 0) {
            for(String itemSeq: regMedicineListAskDto.getItemSeq()){
                List<MedicineInfo> medicineInfos=medicineInfoRepository.findByItemSeq(itemSeq);
                if(medicineInfos.size()!=0){
                    Optional<RegMedicine> regMed=regMedicineRepository.findAllByUserInfoAndMedicineInfo(userInfo,medicineInfos.get(0));
                    if(!regMed.isPresent()){
                        RegMedicine regMedicine = RegMedicine.builder()
                                .createdAt(LocalDateTime.now())
                                .userInfo(userInfo.get())
                                .medicineInfo(medicineInfos.get(0))
                                .state(BooleanType.BEGIN)
                                .build();
                        regMedicineRepository.save(regMedicine);
                    }
                    else {
                        regMed.get().update();
                        regMedicineRepository.save(regMed.get());
                    }
                }
            }
        }


    }
    public void addRegMedicine(RegmedicineAskDto regmedicineAskDto) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(regmedicineAskDto.getUserId());
        List<MedicineInfo> medicineInfo=medicineInfoRepository.findByItemSeq(regmedicineAskDto.getItemSeq());
        Optional<RegMedicine> regMed=regMedicineRepository.findAllByUserInfoAndMedicineInfo(userInfo,medicineInfo.get(0)); //중복등록방지지
        if(!regMed.isPresent()){
            RegMedicine regMedicine = RegMedicine.builder()
                    .createdAt(LocalDateTime.now())
                    .userInfo(userInfo.get())
                    .medicineInfo(medicineInfo.get(0))
                    .state(BooleanType.BEGIN)
                    .build();
                regMedicineRepository.save(regMedicine);
        }
        else {
            regMed.get().update();
            regMedicineRepository.save(regMed.get());
        }
    }

    public List<SumMedInfo> getUserRegMedicines(String userId) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(userId);
        List<SumMedInfo> sumMedInfos=new ArrayList<>();
        if(userInfo.isPresent()) {
            ArrayList<RegMedicine> regMedicines = regMedicineRepository.findAllByUserInfo(userInfo.get()); // 유저가 등록한 알약 등록
            for(RegMedicine regMedicine:regMedicines) {
                if (regMedicine.getState().equals(BooleanType.BEGIN)) {//알약 삭제를 안한 목록 추출
                    SumMedInfo sumMedInfo = new SumMedInfo(regMedicine);
                    sumMedInfos.add(sumMedInfo);
                }
            }
        }// if(userInfo.isPresent());
        return sumMedInfos;
    }
    public Message deleteRegMedicine(long registerId) {
        Optional<RegMedicine> regMedicines = regMedicineRepository.findById(registerId);
        if(regMedicines.isPresent()){
            regMedicines.get().finish();
            regMedicineRepository.save(regMedicines.get());
            return Message.builder().resultCode(ResultCode.OK).build();
        }
        return Message.builder().resultCode(ResultCode.FAIL).build();
    }
}