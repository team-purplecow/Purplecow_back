package com.purplecow.junction.repository;

import com.purplecow.junction.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository <Users, Integer> {

    List<Users> findByNameContaining(String name);
    List<Users> findAllByOrderByCreatedDateDesc();

}
