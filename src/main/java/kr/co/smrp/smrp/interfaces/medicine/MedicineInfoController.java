package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineInfoService;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.dto.medicine.AddMedicineInfoAskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MedicineInfoController {
    @Autowired
    MedicineInfoService medicineInfoService;
    @PostMapping("medicine/add")
    public ResponseEntity addMedicine(@RequestBody AddMedicineInfoAskDto addMedicineInfoAskDto) throws URISyntaxException {
        medicineInfoService.add(addMedicineInfoAskDto);
        return ResponseEntity.created(new URI("medicine/add/"+addMedicineInfoAskDto.getItemSeq())).body("{}");
    }
}
