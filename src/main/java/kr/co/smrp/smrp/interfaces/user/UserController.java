package kr.co.smrp.smrp.interfaces.user;

import kr.co.smrp.smrp.application.user.UserService;
import kr.co.smrp.smrp.dto.Message.Message;
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
    public Message join(@RequestBody JoinUserAskDto joinUserAskDto) {
            return userService.join(joinUserAskDto);
    }

    @PostMapping("/user/login")
    public UserDto login(@RequestBody UserIdPwDto userIdPwDto) {
        return userService.login(userIdPwDto);
    }

    @GetMapping("user/findId")
    public String findId(@RequestParam String name, @RequestParam String email) {
        return userService.findId(name,email);
    }
    @GetMapping("user/idCheck")
    public Message findIdCheck(@RequestParam String userId) {
        return userService.findIdCheck(userId);
    }

    @PostMapping("/user/Info")
    public UserDto getUserInfo(@RequestBody UserIdPwDto userIdPwDto) {
        return userService.getUserInfo(userIdPwDto);

    }
}
