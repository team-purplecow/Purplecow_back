package com.purplecow.junction.dto;

import com.purplecow.junction.domain.Event;
import com.purplecow.junction.service.EventService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EventSaveDto {

    @Schema(description = "행사 이름", example = "정션")
    private String title;

    @Schema(description = "누적 참가자", example = "500")
    private int number;

    @Schema(description = "시작 일자", example = "2023-08-06")
    private LocalDate startDate;

    @Schema(description = "마감 일자", example = "2023-08-26")
    private LocalDate finishDate;

    @Builder
    public EventSaveDto(String title, int number, LocalDate startDate, LocalDate finishDate){
        this.title=title;
        this.number=number;
        this.startDate=startDate;
        this.finishDate=finishDate;
    }
    public Event toEntity(){
        return Event.builder()
                .title(title)
                .number(number)
                .startDate(startDate)
                .finishDate(finishDate)
                .build();
    }

}
