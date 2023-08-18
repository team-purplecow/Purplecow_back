package com.purplecow.junction;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class sample {
    @RequestMapping("/sample")
    public String greeting(){
        return "테스트";
    }

}
