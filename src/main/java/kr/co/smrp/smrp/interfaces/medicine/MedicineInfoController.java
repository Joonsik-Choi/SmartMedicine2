package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineInfoService;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.ConMedicineAskDto;
import kr.co.smrp.smrp.dto.medicine.AddMedicineInfoAskDto;
import kr.co.smrp.smrp.dto.medicine.MedicineInfoRsponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MedicineInfoController {
    @Autowired
    MedicineInfoService medicineInfoService;

    @PostMapping("medicine/add")
    public ResponseEntity addMedicine(@RequestBody AddMedicineInfoAskDto addMedicineInfoAskDto) throws URISyntaxException { //약추가하기
        medicineInfoService.add(addMedicineInfoAskDto);
        return ResponseEntity.created(new URI("medicine/add/"+addMedicineInfoAskDto.getItemSeq())).body("{}");
    }
    @PostMapping("medicine/search/condition")
    public ArrayList<MedicineInfoRsponDTO> getConMedicine(@RequestBody ConMedicineAskDto conMedicineAskDto){ //알약 선택 조건 검사
       return (ArrayList<MedicineInfoRsponDTO>) medicineInfoService.getConMedicineInfo(conMedicineAskDto);
    }
    @GetMapping("medicine/search")
    public MedicineInfoRsponDTO getMedicine(@RequestParam String itemSeq){
        return medicineInfoService.getMedicineInfo(itemSeq);
    }
}
