package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineAlarmService;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmAskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

@RestController
public class MedicineAlarmController {
    @Autowired
    private MedicineAlarmService medicineAlarmService;
    @GetMapping("medicine/alarm")
    public ArrayList<MedicineAlarm> getMedicineAlarm(@RequestParam String userId){
       return (ArrayList<MedicineAlarm>) medicineAlarmService.getMedicineAlarm(userId);
    }
    @PostMapping("medicine/alarm/add")
    public ResponseEntity addMedicineAlarm(@RequestBody MedicineAlarmAskDto medicineAlarmAskDto) throws  URISyntaxException {
        medicineAlarmService.addMedicineAlarm(medicineAlarmAskDto);
        return ResponseEntity.created(new URI("medicine/alarm/add/"+medicineAlarmAskDto.getUserId())).body("{}");
    }
    @DeleteMapping("medicine/alarm/delete")
    public Message deleteMedicineAlarm(@RequestParam Long medicineAlarmId){
        return medicineAlarmService.deleteMedicineAlarm(medicineAlarmId);
    }
}
