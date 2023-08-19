package com.purplecow.junction.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Setter
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name = "event_idx")
    private Event event;

    @Column(nullable = false, length = 100)
    @Schema(description = "기업 이름", example = "Solum")
    private String title;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @Schema(description = "누적 참가자", example = "500")
    private int number;

    @Builder
    public Company(Event event, String title, int number){
        this.event=event;
        this.title=title;
        this.number=number;
    }
    public void increaseNumber(){
        this.number++;
    }

}
