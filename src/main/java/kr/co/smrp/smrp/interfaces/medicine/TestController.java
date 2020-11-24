package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.medicine.TestService;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
import kr.co.smrp.smrp.dto.medicine.MedicineImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @PostMapping("/medicine/uploadImage")
    public Message uploadImage(@RequestBody ArrayList<MultipartFile> files){
        System.out.println("이미지 업로드");
        try {
            String baseDir = "C:\\Users\\wnstl";
            File front = new File(baseDir + "\\" + files.get(0).getOriginalFilename());
            File back=new File(baseDir + "\\" + files.get(1).getOriginalFilename());
            files.get(0).transferTo(front);
            files.get(1).transferTo(back);

            System.out.println(front.getName()+" " + front.getPath());
            System.out.println(back.getName()+" " + back.getPath());
            front.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Message.builder().resultCode(ResultCode.OK).build();
    }
    @GetMapping("/test/test/test")
    public void tensorflowTest() throws IOException, URISyntaxException {
        testService.test();
    }
}
