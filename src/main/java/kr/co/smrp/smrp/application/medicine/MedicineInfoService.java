package kr.co.smrp.smrp.application.medicine;

import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfoRepository;
import kr.co.smrp.smrp.dto.medicine.AddMedicineInfoAskDto;
import org.springframework.stereotype.Service;

@Service
public class MedicineInfoService {
    private MedicineInfoRepository medicineInfoRepository;
    public MedicineInfoService(MedicineInfoRepository medicineInfoRepository){
        this.medicineInfoRepository=medicineInfoRepository;
    }

    public void add(AddMedicineInfoAskDto addMedicineInfoAskDto) {
        MedicineInfo medicineInfo=addMedicineInfoAskDto.toEntity();
        medicineInfoRepository.save(medicineInfo);
    }
}
