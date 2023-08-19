package com.purplecow.junction.controller;

import com.purplecow.junction.domain.Company;
import com.purplecow.junction.dto.CompanySaveDto;
import com.purplecow.junction.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
