package kr.co.smrp.smrp.domain.user.Inquiry;

import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    ArrayList<Inquiry> findAllByUserInfo(UserInfo userInfo);
}
