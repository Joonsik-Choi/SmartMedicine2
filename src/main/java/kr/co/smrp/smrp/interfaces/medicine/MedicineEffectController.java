package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineEffectService;
import kr.co.smrp.smrp.application.medicine.MedicineInfoService;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.medicine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class MedicineEffectController {
    @Autowired
    MedicineInfoService medicineInfoService;
    @Autowired
    MedicineEffectService medicineEffectService;

    @PostMapping("medicine/addMedicineEffect")
    public Message addMedicineEffect(@RequestBody ArrayList<MedicineEffectAskDto> medicineEffectAskDtos){
        return medicineEffectService.addListEffect(medicineEffectAskDtos);
    }
    @GetMapping("medicine/getEffect")
    public String getEffect(@RequestParam Long id){
        return medicineEffectService.getEffect(id);
    }

    @PostMapping("medicine/getEffect1111")
    public String getEffect1111(@RequestBody ArrayList<ItemSeq> itemSeqs){
        return medicineEffectService.getEffect1111(itemSeqs);
    }
    @PostMapping("/medicine/transfer")
    public String transferEffect(@RequestBody ArrayList<MedicineEffectTransfer> medicineEffectTransfer) throws IOException {
        return medicineEffectService.transferEffect(medicineEffectTransfer);
    }

}
