package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.user.InquiryService;
import kr.co.smrp.smrp.domain.user.Inquiry.Inquiry;
import kr.co.smrp.smrp.dto.user.InquiryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/userInfo/inquiry/add")
    public ResponseEntity addInquiry(@RequestBody InquiryDto inquiry) throws URISyntaxException {
        inquiryService.addInquiry(inquiry);
        return ResponseEntity.created(new URI("/userInfo/inquiry/"+inquiry.getUserId())).body("{\"resultCode\": \"OK\"}");
    }
    @GetMapping("/userInfo/inquiry/get")
    public ArrayList<Inquiry> getInquiry(@RequestParam String userId){
        return inquiryService.getInquiry(userId);
    }


}
