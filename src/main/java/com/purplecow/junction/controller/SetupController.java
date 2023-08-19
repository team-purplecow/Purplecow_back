package com.purplecow.junction.controller;

import com.purplecow.junction.domain.*;
import com.purplecow.junction.repository.CompanyRepository;
import com.purplecow.junction.repository.EventRepository;
import com.purplecow.junction.repository.UsersRepository;
import com.purplecow.junction.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/setup")
public class SetupController {
    private final EventRepository eventRepository;
    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;
    private final VisitRepository visitRepository;

    private static final String[] COMPANY_NAMES = {"SOLUM", "SAMSUNG", "NAVER", "LG"};
    private final Random randomGenerator = new Random(); // 변수 이름 변경


    private static Users generateRandomUser() {
        Random random = new Random();
        Users user = new Users();

        String[] names = {"Alice", "Bob", "Charlie", "David", "Ella", "Frank", "Grace", "Henry", "Ivy", "Jack", "Shine","Morris","Cedric","Mary","Currly","James",
        "Wayn","Barbie","Ken","Cleric","Baba","Harry","Kay","Medi","Haely","Elice","Gola"};
        user.setName(names[random.nextInt(names.length)]);

        user.setAge(random.nextInt(10) + 30); // 20 ~ 39 사이의 나이 생성

        char[] genders = {'M', 'W'};
        user.setGender(genders[random.nextInt(genders.length)]);

        Position[] positions = Position.values();
        user.setPosition(positions[random.nextInt(positions.length)]);

        Job[] jobs = Job.values();
        user.setJob(jobs[random.nextInt(jobs.length)]);

        Food[] foods = Food.values();
        user.setFood(foods[random.nextInt(foods.length)]);

        StringBuilder phoneBuilder = new StringBuilder("010");
        for (int i = 0; i < 8; i++) {
            phoneBuilder.append(random.nextInt(10));
        }
        user.setPhone(phoneBuilder.toString());
        return user;

    }

    @GetMapping("/")
    public void setup() {
        LocalDate startDate = LocalDate.parse("2023-08-18");
        LocalDate endDate = LocalDate.parse("2023-08-20");
        Event event = new Event("JUNCTION ASIA 2023", 0, startDate, endDate);
        eventRepository.save(event);

        for (String companyName : COMPANY_NAMES) {
            Company company = new Company(event, companyName, 0);
            companyRepository.save(company);
        }

        for (int i = 0; i < 200; i++) {
            Users user = generateRandomUser();
            usersRepository.save(user);
            // Visit 생성 및 저장
            Visit visit = new Visit(event, user);
            visitRepository.save(visit);

            int randomCompanyIndex = randomGenerator.nextInt(COMPANY_NAMES.length); // 변수 이름 변경
            String companyName = COMPANY_NAMES[randomCompanyIndex];

            CompanyVisit companyVisit = new CompanyVisit(companyName, null); // 시간 정보를 빼고 생성
            visit.getCompanyVisitList().add(companyVisit);

            Company company = companyRepository.findByTitle(companyName);
            company.increaseNumber();
            companyRepository.save(company);

            visitRepository.save(visit);
        }
    }

}
