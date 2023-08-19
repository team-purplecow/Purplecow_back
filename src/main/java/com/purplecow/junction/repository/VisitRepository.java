package com.purplecow.junction.repository;

import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Users;
import com.purplecow.junction.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    Visit findByEventAndUser(Event event, Users user);
}
