package com.purplecow.junction.controller;


import com.purplecow.junction.domain.Position;
import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "users", description = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    @PostMapping("/users/signup")
    @Operation(summary = "api/users/signup", description = "회원가입")
    public ResponseEntity<Users> saveUsers(@RequestBody UserSaveDto userSaveDto){
        Users user =userService.saveUsers(userSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/{user_idx}")
    @Operation(summary = "api/users/1", description = "유저 정보")
    public ResponseEntity<Users> findById(@PathVariable int user_idx){
        Users user= userService.findById(user_idx);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/search")
    @Operation(summary = "api/users/search", description = "검색 결과")
    public ResponseEntity<List<Users>> findBy(@RequestParam String keyword) {
        List<Users> users = userService.findByName(keyword);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build(); // 검색 결과가 없을 때
        } else {
            return ResponseEntity.ok(users); // 검색 결과가 있을 때
        }
    }

    @GetMapping("/users")
    @Operation(summary = "api/users", description = "모든 유저 리스트 최신순 조회")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/gender")
    @Operation(summary = "api/users/gender", description = "성별에 따른 유저 리스트 조회")
    public ResponseEntity<List<Users>> getUsersByGender(@RequestParam("gender") char gender) {
        List<Users> filteredUsers = userService.getUsersByGender(gender);
        return ResponseEntity.ok(filteredUsers);
    }

    @GetMapping("/users/position")
    @Operation(summary = "api/users/position", description = "성별에 따른 유저 리스트 조회")
    public ResponseEntity<List<Users>> getUsersByGender(@RequestParam("Position") Position position) {
        List<Users> filteredUsers = userService.getUsersByPosition(position);
        return ResponseEntity.ok(filteredUsers);
    }

    @GetMapping("/users/age")
    @Operation(summary = "api/users/age-group", description = "나이 그룹에 따른 유저 리스트 조회")
    public ResponseEntity<List<Users>> getUsersByAgeGroup(@RequestParam("age") int age) {
        List<Users> filteredUsers = userService.getUsersByAge(age);
        return ResponseEntity.ok(filteredUsers);
    }


    @GetMapping("users/percentage")
    @Operation(summary = "api/users/percentage", description = "유저 비율")
    public ResponseEntity<Map<Character, Double>> getGenderPercentage() {
        Map<Character, Double> genderPercentage = userService.getGenderPercentage();
        return ResponseEntity.ok(genderPercentage);
    }

}
