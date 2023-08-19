package com.purplecow.junction.repository;

import com.purplecow.junction.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
