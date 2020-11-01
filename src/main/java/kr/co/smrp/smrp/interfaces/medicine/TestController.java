package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.MedicineImageDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class TestController {
    @PostMapping("/medicine/uploadImage")
    public Message uploadImage(@RequestBody MedicineImageDto medicineImageDto){
        try {
            String baseDir = "C:\\Users\\wnstl";
            File front = new File(baseDir + "\\" + medicineImageDto.getPront().getOriginalFilename());
            File back=new File(baseDir + "\\" + medicineImageDto.getBack().getOriginalFilename());
            medicineImageDto.getPront().transferTo(front);
            medicineImageDto.getBack().transferTo(back);
            System.out.println(front.getName()+" " + front.getPath());
            System.out.println(back.getName()+" " + back.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Message.builder().resultCode(ResultCode.OK).build();
    }
}
