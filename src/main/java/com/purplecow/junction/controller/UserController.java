package com.purplecow.junction.controller;


import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users", description = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    @Operation(summary = "users/signup", description = "회원가입")
    public  ResponseEntity<Users> saveUsers(@RequestBody UserSaveDto userSaveDto){
        Users user =userService.saveUsers(userSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
