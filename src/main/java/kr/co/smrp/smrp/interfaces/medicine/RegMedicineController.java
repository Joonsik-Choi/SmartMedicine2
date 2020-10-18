package kr.co.smrp.smrp.interfaces.medicine;
import kr.co.smrp.smrp.application.medicine.RegMedicineService;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.RegmedicineAskDto;
import kr.co.smrp.smrp.dto.medicine.SumMedInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class RegMedicineController {
    @Autowired
    private RegMedicineService regMedicineService;
    @GetMapping("/medicine/registers")
    public ArrayList<SumMedInfo> getRegMedicines(@RequestParam String userId) {
        return (ArrayList<SumMedInfo>) regMedicineService.getUserRegMedicines(userId);
    }

    @PostMapping("/medicine/register/add")
    public ResponseEntity addRegMedicine(@RequestBody RegmedicineAskDto regmedicineAskDto) throws URISyntaxException {
         regMedicineService.addRegMedicine(regmedicineAskDto);
               return ResponseEntity.created(new URI("/medicine/register/" + regmedicineAskDto.getUserId())).body("{ \"resultCode\": \"OK\"}");
           }
    @DeleteMapping("medicine/register/delete")
    public Message delRegMedicine(@RequestParam long registerId){
        return regMedicineService.deleteRegMedicine(registerId);
    }
}