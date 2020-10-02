package kr.co.smrp.smrp.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String[] hello(){
        return new String[]{"abc", "def"};
    }
}
