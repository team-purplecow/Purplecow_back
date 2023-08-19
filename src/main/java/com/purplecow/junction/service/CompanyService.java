package com.purplecow.junction.service;

import com.purplecow.junction.domain.Company;
import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Users;
import com.purplecow.junction.dto.CompanySaveDto;
import com.purplecow.junction.repository.CompanyRepository;
import com.purplecow.junction.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EventRepository eventRepository;

    @Transactional
    public Company saveCompany(@RequestBody CompanySaveDto companySaveDto) {
        String eventTitle = companySaveDto.getEventTitle();
        Event event = eventRepository.findByTitle(eventTitle);

        if (event == null) {
            throw new IllegalArgumentException("Event not found for title: " + eventTitle);
        }
        Company company = companySaveDto.toEntity(event);
        return companyRepository.save(company);
    }
    @Transactional
    public Company findByName(String name){
        return companyRepository.findByTitle(name);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
