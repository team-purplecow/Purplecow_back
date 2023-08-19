package com.purplecow.junction.dto;


import com.purplecow.junction.domain.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class EventResponseDto {

    @Schema(description = "행사 제목", example = "정션")
    private String eventTitle;

    @Schema(description = "시작 일자", example = "2023-08-06")
    private LocalDate startDate;

    @Schema(description = "마감 일자", example = "2023-08-26")
    private LocalDate finishDate;

    @Schema(description = "누적 참가자", example = "500")
    private int total;

    //포지션 이름, 수 리스트
    @Schema(description = "포지션 정보")
    private Map<String, Integer> positionInfo;  // 포지션 이름과 수를 담는 Map



    //statistics 리스트

    //gen & age 항목, 퍼센트 리스트

    //booth 이름/숫자


    public void addPositionInfo(Position position, int count) {
        if (positionInfo == null) {
            positionInfo = new HashMap<>();
        }
        positionInfo.put(position.name(), count);
    }

    public Map<String, Integer> getPositionInfo() {
        return positionInfo;
    }

}
