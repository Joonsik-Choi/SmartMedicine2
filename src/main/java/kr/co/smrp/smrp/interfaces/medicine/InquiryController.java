package kr.co.smrp.smrp.interfaces.medicine;

import kr.co.smrp.smrp.application.user.InquiryService;
import kr.co.smrp.smrp.domain.user.Inquiry.Inquiry;
import kr.co.smrp.smrp.dto.user.InquiryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/userInfo/inquiry")
    public ResponseEntity addInquiry(@RequestBody InquiryDto inquiry) throws URISyntaxException {
        inquiryService.addInquiry(inquiry);
        return ResponseEntity.created(new URI("/userInfo/inquiry/"+inquiry.getUserId())).body("{\"resultCode\": \"OK\"}");
    }

}
