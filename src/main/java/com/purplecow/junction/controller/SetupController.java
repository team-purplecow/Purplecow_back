package com.purplecow.junction.controller;

import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Visit;
import com.purplecow.junction.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setup")
public class SetupController {
    private final EventRepository eventRepository;
    @GetMapping("/")
    public void setup() {
        LocalDate startDate = LocalDate.parse("2023-08-18");
        LocalDate endDate = LocalDate.parse("2023-08-20");
        Event event = new Event("JUNCTION ASIA 2023", 0, startDate, endDate);
        eventRepository.save(event);
    }

}
