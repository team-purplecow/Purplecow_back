package com.purplecow.junction.repository;

import com.purplecow.junction.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByTitle(String name);
}
