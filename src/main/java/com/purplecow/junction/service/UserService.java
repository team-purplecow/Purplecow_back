package com.purplecow.junction.service;


import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

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
}
