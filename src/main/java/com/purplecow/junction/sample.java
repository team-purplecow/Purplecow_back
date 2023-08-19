package com.purplecow.junction;

import com.purplecow.junction.domain.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class sample {
    @RequestMapping("/sample")
    public String greeting(){
        Users user = new Users("i");

        return "테스트";
    }

}
