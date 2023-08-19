package com.purplecow.junction.domain;

import com.purplecow.junction.BaseTimeEntity;
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
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false, length = 20)
    @Schema(description = "name", example = "이름")
    private String name;

    @Column(nullable = false)
    @Schema(description = "나이", example = "26")
    private int age;

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
    public Users(int age,String name, char gender, Position position, Job job,Food food, String  phone){
        this.age = age;
        this.name=name;
        this.gender=gender;
        this.position=position;
        this.job=job;
        this.food=food;
        this.phone=phone;
    }
}
