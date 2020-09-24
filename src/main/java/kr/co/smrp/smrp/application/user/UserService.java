package kr.co.smrp.smrp.application.user;

import kr.co.smrp.smrp.domain.user.userInfo.UserInfo;
import kr.co.smrp.smrp.domain.user.userInfo.UserInfoRepository;
import kr.co.smrp.smrp.dto.Message.Message;
import kr.co.smrp.smrp.dto.Message.ResultCode;
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
    public Message join(JoinUserAskDto joinUserAskDto) {
        UserInfo userInfo= userInfoRepository.save(joinUserAskDto.toEntity());
        return Message.builder().resultCode(ResultCode.OK).build();
    }

    public Message login(UserIdPwDto userIdPwDto) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndUserPw(userIdPwDto.getUserId(), userIdPwDto.getPassWord());
        if(userInfo.isPresent())
            return Message.builder().resultCode(ResultCode.PASS).build();
        return Message.builder().resultCode(ResultCode.FAIL).build();
    }
    public Message findId(String userId) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserId(userId);
        if(userInfo.isPresent())
            return Message.builder().resultCode(ResultCode.OK).build();
        return Message.builder().resultCode(ResultCode.FAIL).build();
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
