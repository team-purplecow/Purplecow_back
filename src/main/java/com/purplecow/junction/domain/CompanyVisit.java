package com.purplecow.junction.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class CompanyVisit {
    private String companyName;
    private LocalDateTime dateTime;

    protected CompanyVisit(){}

    @Builder
    public CompanyVisit(String companyName, LocalDateTime dateTime){
        this.companyName=companyName;
        this.dateTime=dateTime;
    }
}
