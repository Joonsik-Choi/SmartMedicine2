package kr.co.smrp.smrp.application.user;

import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.user.JoinUserAskDto;
import kr.co.smrp.smrp.dto.user.UserDto;
import kr.co.smrp.smrp.dto.user.UserIdPwDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserInfoRepository userInfoRepository;
    public UserService(UserInfoRepository userInfoRepository){
        this.userInfoRepository=userInfoRepository;
    }
    public String add(JoinUserAskDto joinUserAskDto) {
        UserInfo userInfo= userInfoRepository.save(joinUserAskDto.toEntity());
        return "ok";
    }

    public String login(UserIdPwDto userIdPwDto) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndUserPw(userIdPwDto.getUserId(), userIdPwDto.getPassWord());
        if(userInfo.isPresent())
            return "ok";
        return "fail";
    }
    public String findId(String userId) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserId(userId);
        if(userInfo.isPresent())
            return userInfo.get().getUserId();
        return "Not Found";
    }

    public UserDto getUserInfo(UserIdPwDto userIdPwDto) {
        Optional<UserInfo> userInfo=userInfoRepository.findByUserIdAndUserPw(userIdPwDto.getUserId(), userIdPwDto.getPassWord());
        if(userInfo.isPresent()) {
            UserDto userDto = new UserDto(userInfo.get());
            return  userDto;
        }
        else{
            return UserDto.builder().build();
        }
    }
}
