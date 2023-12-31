package com.purplecow.junction.service;


import com.purplecow.junction.domain.Position;
import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users saveUsers(@RequestBody UserSaveDto userSaveDto){
        Users user = userSaveDto.toEntity();
        usersRepository.save(user);
        return user;
    }

    @Transactional
    public Users findById(int idx){
        Users entity = usersRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다. id="+idx));
        return entity;
    }

    @Transactional
    public List<Users> findByName(String name){
        return usersRepository.findByNameContaining(name);
    }
    @Transactional
    public List<Users> getAllUsers() {
        return usersRepository.findAllByOrderByCreatedDateDesc();
    }

    public Map<Character, Double> getGenderPercentage() {
        List<Users> usersList = usersRepository.findAll();

        long totalUsers = usersList.size();
        Map<Character, Long> genderCount = usersList.stream()
                .collect(Collectors.groupingBy(Users::getGender, Collectors.counting()));

        Map<Character, Double> genderPercentage = new HashMap<>();
        genderCount.forEach((gender, count) -> {
            double percentage = (count * 100.0) / totalUsers;
            genderPercentage.put(gender, percentage);
        });

        return genderPercentage;
    }

    public List<Users> getUsersByGender(char gender) {
        return usersRepository.findByGender(gender);
    }

    public List<Users> getUsersByPosition(Position position) {
        return usersRepository.findByPosition(position);
    }


    public List<Users> getUsersByAge(int ageGroup) {
        List<Users> allUsers = usersRepository.findAll();

        int minAge, maxAge;
        switch (ageGroup) {
            case 1:
                minAge = 10;
                maxAge = 20;
                break;
            case 2:
                minAge = 21;
                maxAge = 30;
                break;
            case 3:
                minAge = 31;
                maxAge = 40;
                break;
            default:
                minAge = 0;
                maxAge = Integer.MAX_VALUE;
        }

        return allUsers.stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .collect(Collectors.toList());
    }


}
