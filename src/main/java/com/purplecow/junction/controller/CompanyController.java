package com.purplecow.junction.controller;

import com.purplecow.junction.domain.Company;
import com.purplecow.junction.dto.CompanyResponseDto;
import com.purplecow.junction.dto.CompanySaveDto;
import com.purplecow.junction.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "company", description = "기업")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("company/save")
    @Operation(summary = "api/company/save", description = "기업 저장")
    public ResponseEntity<Company> saveCompany(@RequestBody CompanySaveDto companySaveDto){
        Company company = companyService.saveCompany(companySaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping("company/list")
    @Operation(summary = "api/company/list", description = "기업 리스트")
    public List<CompanyResponseDto> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return companies.stream()
                .map(company -> new CompanyResponseDto(company.getTitle(), company.getNumber()))
                .collect(Collectors.toList());
    }

    @GetMapping("company/listToday")
    @Operation(summary = "api/company/list", description = "기업 리스트")
    public List<CompanyResponseDto> getAllCompanie() {
        List<Company> companies = companyService.getAllCompanies();
        return companies.stream()
                .map(company -> new CompanyResponseDto(company.getTitle(), company.getNumber()/3))
                .collect(Collectors.toList());
    }
}
