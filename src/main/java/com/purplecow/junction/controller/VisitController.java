package com.purplecow.junction.controller;

import com.purplecow.junction.domain.*;
import com.purplecow.junction.dto.EventResponseDto;
import com.purplecow.junction.dto.UserSaveDto;
import com.purplecow.junction.repository.CompanyRepository;
import com.purplecow.junction.repository.EventRepository;
import com.purplecow.junction.repository.VisitRepository;
import com.purplecow.junction.service.CompanyService;
import com.purplecow.junction.service.EventService;
import com.purplecow.junction.service.UserService;
import com.purplecow.junction.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Tag(name = "visit", description = "입장")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VisitController {
    private final UserService userService;
    private final EventService eventService;
    private final EventRepository eventRepository;
    private final VisitRepository visitRepository;
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    private final VisitService visitService;


    //행사 입장 (QR찍고 입장)
    @PostMapping("/visit/checkin/{user_idx}")
    @Operation(summary = "api/visit/checkin/1", description = "유저 체크인")
    public ResponseEntity<Visit> saveUsers(@PathVariable int user_idx){
        Users user= userService.findById(user_idx);
        Event event = eventService.findById(1);
        event.increaseNumber();
        eventRepository.save(event);

        Visit existingVisit = visitRepository.findByEventAndUser(event, user);

        if (existingVisit != null) {
            return ResponseEntity.ok(existingVisit);
        }

        Visit visit = new Visit(event, user);
        visitRepository.save(visit);

        return ResponseEntity.status(HttpStatus.CREATED).body(visit);
    }

    //부스 이동해서 방문
    @PutMapping("visit/company/{user_idx}")
    @Operation(summary = "api/visit/company/1", description = "기업 방문")
    public ResponseEntity<Visit> visitCompany(@PathVariable int user_idx, @RequestParam String companyName){
        LocalDateTime current = LocalDateTime.now();

        Users user = userService.findById(user_idx);
        Event event = eventService.findById(1);

        Visit visit = visitRepository.findByEventAndUser(event, user);
        if (visit == null) {
            visit = new Visit(event, user);
        }

        boolean companyVisitExists = visit.getCompanyVisitList().stream()
                .anyMatch(cv -> cv.getCompanyName().equals(companyName));

        if (!companyVisitExists) {
            CompanyVisit companyVisit = new CompanyVisit(companyName, current);
            visit.getCompanyVisitList().add(companyVisit);

            Company company = companyService.findByName(companyName);
            company.increaseNumber();
            companyRepository.save(company);

            visitRepository.save(visit);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(visit);
    }


}
