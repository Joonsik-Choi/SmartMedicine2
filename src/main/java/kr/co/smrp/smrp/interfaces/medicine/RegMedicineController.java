package kr.co.smrp.smrp.interfaces.medicine;
import kr.co.smrp.smrp.application.medicine.RegMedicineService;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.medicine.register.RegMedicineListAskDto;
import kr.co.smrp.smrp.dto.medicine.register.RegmedicineAskDto;
import kr.co.smrp.smrp.dto.medicine.info.SumMedInfo;
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
    @PostMapping("/medicine/register/addList") //사용자 계정에 여러개의 약을 한번에 등록하는  루트
    public ResponseEntity addRegMedicineList(@RequestBody RegMedicineListAskDto regMedicineListAskDto) throws URISyntaxException {
        regMedicineService.addRegMedicineList(regMedicineListAskDto);
        return ResponseEntity.created(new URI("/medicine/register/" + regMedicineListAskDto.getUserId())).body("{ \"resultCode\": \"OK\"}");
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