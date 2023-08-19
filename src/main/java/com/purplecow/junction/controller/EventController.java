package com.purplecow.junction.controller;


import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Position;
import com.purplecow.junction.domain.Users;
import com.purplecow.junction.domain.Visit;
import com.purplecow.junction.dto.EventResponseDto;
import com.purplecow.junction.dto.EventSaveDto;
import com.purplecow.junction.service.EventService;
import com.purplecow.junction.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "events", description = "행사")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
    private final VisitService visitService;

    @PostMapping("event/save")
    @Operation(summary = "api/event/save", description = "행사 추가")
    public ResponseEntity<Event> saveEvent(@RequestBody EventSaveDto eventSaveDto){
        Event event = eventService.saveEvent(eventSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }


    //기본 보드
    @GetMapping("/event/{event_idx}")
    @Operation(summary = "api/event/1", description = "행사 운영사 정보 확인")
    public ResponseEntity<EventResponseDto> findEventById(@PathVariable int event_idx) {
        Event event = eventService.findById(event_idx);
        List<Visit> visits = visitService.findByEvent(event);

        EventResponseDto responseDto = new EventResponseDto();
        responseDto.setEventTitle(event.getTitle());
        responseDto.setStartDate(event.getStartDate());
        responseDto.setFinishDate(event.getFinishDate());
        responseDto.setTotal(visits.size());

        Map<Position, Integer> positionCounts = new HashMap<>();
        for (Visit visit : visits) {
            Position position = visit.getUser().getPosition();
            positionCounts.put(position, positionCounts.getOrDefault(position, 0) + 1);
        }

        for (Map.Entry<Position, Integer> entry : positionCounts.entrySet()) {
            responseDto.addPositionInfo(entry.getKey(), entry.getValue());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //최근 2주, 1달 변경

    //총 visit 수 return

}
