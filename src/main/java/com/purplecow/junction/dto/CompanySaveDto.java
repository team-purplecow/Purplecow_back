package com.purplecow.junction.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.purplecow.junction.domain.Company;
import com.purplecow.junction.domain.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
public class CompanySaveDto {

    @Schema(description = "행사 제목", example = "정션")
    private String eventTitle;

    @Schema(description = "기업 이름", example = "Solum")
    private String title;

    public Company toEntity(Event event){
        return Company.builder()
                .event(event)
                .title(title)
                .build();
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

}
