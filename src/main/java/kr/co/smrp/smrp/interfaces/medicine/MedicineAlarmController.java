package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.MedicineAlarmService;
import kr.co.smrp.smrp.domain.medicine.MedicineAlarm.MedicineAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MedicineAlarmController {
    @Autowired
    private MedicineAlarmService medicineAlarmService;


    @GetMapping("medicine/alarm")
    public ArrayList<MedicineAlarm> getMedicineAlarm(String userId){
       return (ArrayList<MedicineAlarm>) medicineAlarmService.getMedicineAlarm(userId);
    }
}
