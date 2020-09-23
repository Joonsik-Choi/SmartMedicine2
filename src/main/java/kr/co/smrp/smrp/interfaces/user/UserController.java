package kr.co.smrp.smrp.interfaces.user;

import kr.co.smrp.smrp.application.user.UserService;
import kr.co.smrp.smrp.dto.user.JoinUserAskDto;
import kr.co.smrp.smrp.dto.user.UserDto;
import kr.co.smrp.smrp.dto.user.UserIdPwDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
   private UserService userService;
    @PostMapping("/user/join")
    public String join(@RequestBody JoinUserAskDto joinUserAskDto) {
            return userService.add(joinUserAskDto);
    }
    @PostMapping({"/user/login"})
    public String login(@RequestBody UserIdPwDto userIdPwDto) {
        String s= userService.login(userIdPwDto);
        return s;
    }

    @GetMapping({"user/findId"})
    public String findId(@RequestParam String userId) {
        return userService.findId(userId);
    }

    @PostMapping({"/user/Info"})
    public UserDto getUserInfo(@RequestBody UserIdPwDto userIdPwDto) {
        return userService.getUserInfo(userIdPwDto);

    }
}
