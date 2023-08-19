package com.purplecow.junction.controller;


import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "users", description = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/users/signup")
    @Operation(summary = "api/users/signup", description = "회원가입")
    public  ResponseEntity<Users> saveUsers(@RequestBody UserSaveDto userSaveDto){
        Users user =userService.saveUsers(userSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @GetMapping("/users/{user_idx}")
    @Operation(summary = "api/users/1", description = "유저 정보")
    public ResponseEntity<Users> findById(@PathVariable int user_idx){
        Users user= userService.findById(user_idx);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
