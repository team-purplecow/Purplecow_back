package com.purplecow.junction.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Setter
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false, length = 100)
    @Schema(description = "행사 이름", example = "정션")
    private String title;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @Schema(description = "누적 참가자", example = "500")
    private int number;

    @Column(nullable = false)
    @Schema(description = "시작 일자", example = "2023-08-06")
    private LocalDate startDate;

    @Column(nullable = false)
    @Schema(description = "마감 일자", example = "2023-08-26")
    private LocalDate finishDate;

    @Builder
    public Event(String title, int number, LocalDate startDate, LocalDate finishDate){
        this.title=title;
        this.number=number;
        this.startDate=startDate;
        this.finishDate=finishDate;
    }
    public void increaseNumber(){
        this.number++;
    }

}
