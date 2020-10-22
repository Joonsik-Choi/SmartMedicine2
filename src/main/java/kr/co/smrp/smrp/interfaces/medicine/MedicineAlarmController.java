package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineAlarmService;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmAskDto;
import kr.co.smrp.smrp.dto.medicine.MedicineAlarmResponDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class MedicineAlarmController {
    @Autowired
    private MedicineAlarmService medicineAlarmService;
    @GetMapping("medicine/alarmAll")
    public ArrayList<MedicineAlarmResponDto> getMedicineAlarmAll(@RequestParam String userId){
       return (ArrayList<MedicineAlarmResponDto>) medicineAlarmService.getMedicineAlarmAll(userId);
    }
    @GetMapping("medicine/alarm")
    public MedicineAlarmResponDto getMedicineAlarm(@RequestParam Long medicineAlarmId){
        return medicineAlarmService.getMedicineAlarm(medicineAlarmId);
    }
    @PutMapping("medicine/update")
    public ResponseEntity medicineAlarmUpdate(@RequestBody MedicineAlarmAskDto medicineAlarmAskDto) throws URISyntaxException {
        medicineAlarmService.medicineAlarmUpdate(medicineAlarmAskDto);
        return ResponseEntity.created(new URI("medicine/alarm/add/"+medicineAlarmAskDto.getUserId())).body("{ \"resultCode\": \"OK\"}");
    }
    @PostMapping("medicine/alarm/add")
    public ResponseEntity addMedicineAlarm(@RequestBody MedicineAlarmAskDto medicineAlarmAskDto) throws  URISyntaxException {
        medicineAlarmService.addMedicineAlarm(medicineAlarmAskDto);
        return ResponseEntity.created(new URI("medicine/alarm/add/"+medicineAlarmAskDto.getUserId())).body("{ \"resultCode\": \"OK\"}");
    }
    @DeleteMapping("medicine/alarm/delete")
    public Message deleteMedicineAlarm(@RequestParam Long medicineAlarmId){
        return medicineAlarmService.deleteMedicineAlarm(medicineAlarmId);
    }
}
