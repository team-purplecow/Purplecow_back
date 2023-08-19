package com.purplecow.junction.domain;

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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    @Schema(description = "성별", example = "W")
    private char gender;

    @Enumerated(EnumType.STRING)
    @Schema(description = "포지션", example = "PARTICIPANT")
    private Position position;

    @Enumerated(EnumType.STRING)
    @Schema(description = "직업", example = "STUDENT")
    private Job job;

    @Enumerated(EnumType.STRING)
    @Schema(description = "음식", example = "NORMAL")
    private Food food;

    @Column(nullable = false, length = 20)
    @Schema(description = "phone", example = "010-0000-0000")
    private String phone;


    @Builder
    public Users(char gender, Position position, Job job,Food food, String  phone){
        this.gender=gender;
        this.position=position;
        this.job=job;
        this.food=food;
        this.phone=phone;

    }
}
