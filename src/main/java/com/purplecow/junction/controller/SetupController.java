package com.purplecow.junction.controller;

import com.purplecow.junction.domain.Company;
import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Visit;
import com.purplecow.junction.repository.CompanyRepository;
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
    private final CompanyRepository companyRepository;

    @GetMapping("/")
    public void setup() {
        LocalDate startDate = LocalDate.parse("2023-08-18");
        LocalDate endDate = LocalDate.parse("2023-08-20");
        Event event = new Event("JUNCTION ASIA 2023", 0, startDate, endDate);
        eventRepository.save(event);

        Company company1 = new Company(event, "SOLUM", 0);
        Company company2 = new Company(event, "SAMSUNG", 0);
        Company company3 = new Company(event, "ARIRANG", 0);
        Company company4 = new Company(event, "LG", 0);

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        companyRepository.save(company4);



    }

}
