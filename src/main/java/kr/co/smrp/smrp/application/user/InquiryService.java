package kr.co.smrp.smrp.application.user;

import kr.co.smrp.smrp.domain.user.Inquiry.Inquiry;
import kr.co.smrp.smrp.domain.user.Inquiry.InquiryRepository;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.user.InquiryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InquiryService {
    InquiryRepository inquiryRepository;
    UserInfoRepository userInfoRepository;
    InquiryService(InquiryRepository inquiryRepository,   UserInfoRepository userInfoRepository){
        this.inquiryRepository=inquiryRepository;
        this.userInfoRepository=userInfoRepository;
    }

    public void addInquiry(InquiryDto inquiryDto) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(inquiryDto.getUserId());
        Inquiry inquiry=Inquiry.builder()
                .userInfo(userInfo.get())
                .content(inquiryDto.getContent())
                .build();
                inquiryRepository.save(inquiry);
    }

    public ArrayList<Inquiry> getInquiry(String userId) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserId(userId);
        return inquiryRepository.findAllByUserInfo(userInfo.get());
    }
}
