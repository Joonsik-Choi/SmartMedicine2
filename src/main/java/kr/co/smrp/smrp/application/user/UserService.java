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

    public UserDto login(UserIdPwDto userIdPwDto) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndUserPw(userIdPwDto.getUserId(), userIdPwDto.getPassWord());
        if(userInfo.isPresent()){
            return new UserDto(userInfo.get());
        }
        return new UserDto();

    }
    public String findId(String userId, String email) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndEmail(userId,email);
        if(userInfo.isPresent())
            return userInfo.get().getUserId();
        return "Fail";
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
