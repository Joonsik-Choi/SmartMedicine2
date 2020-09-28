package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineInfoService;
import kr.co.smrp.smrp.dto.medicine.*;
import kr.co.smrp.smrp.domain.medicine.medicineInfo.MedicineInfo;
import kr.co.smrp.smrp.dto.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @PostMapping("medicine/addList")
    public Message addMedicineList(@RequestBody ArrayList<MedicineInfo> medicineInfo){
        return medicineInfoService.addList(medicineInfo);
    }
    @PostMapping("medicine/addMedicineEffect")
    public Message addMedicineEffect(@RequestBody ArrayList<MedicineEffectAskDto> medicineEffectAskDtos){
        return medicineInfoService.addListEffect(medicineEffectAskDtos);
    }
    @GetMapping("medicine/getEffect")
    public String getEffect(@RequestParam Long id){
        return medicineInfoService.getEffect(id);
    }

    @PostMapping("medicine/getEffect1111")
    public String getEffect1111(@RequestBody ArrayList<ItemSeq> itemSeqs){
        return medicineInfoService.getEffect1111(itemSeqs);
    }

    @PostMapping("/medicine/transfer")
    public String transferEffect(@RequestBody ArrayList<MedicineEffectTransfer> medicineEffectTransfer) throws IOException {
        return medicineInfoService.transferEffect(medicineEffectTransfer);
    }
}
