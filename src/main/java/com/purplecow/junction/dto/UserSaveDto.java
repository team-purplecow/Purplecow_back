package com.purplecow.junction.dto;


import com.purplecow.junction.domain.Food;
import com.purplecow.junction.domain.Job;
import com.purplecow.junction.domain.Position;
import com.purplecow.junction.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
public class UserSaveDto {

    @Schema(description = "나이", example = "26")
    private int age;

    @Schema(description = "성별", example = "W")
    private char gender;

    @Schema(description = "포지션", example = "PARTICIPANT")
    private Position position;

    @Schema(description = "직업", example = "STUDENT")
    private Job job;

    @Schema(description = "음식", example = "NORMAL")
    private Food food;

    @Column(nullable = false, length = 20)
    @Schema(description = "phone", example = "010-0000-0000")
    private String phone;

    @Builder
    public UserSaveDto(int age, char gender, Position position, Job job,
                       Food food, String phone){
        this.age=age;
        this.gender=gender;
        this.position=position;
        this.job=job;
        this.food=food;
        this.phone=phone;
    }

    public Users toEntity(){
        return Users.builder()
                .age(age)
                .gender(gender)
                .phone(phone)
                .position(position)
                .job(job)
                .food(food)
                .build();
    }
}
