package com.purplecow.junction.controller;


import com.purplecow.junction.domain.Event;
import com.purplecow.junction.dto.EventSaveDto;
import com.purplecow.junction.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "users", description = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;

    @PostMapping("event/save")
    @Operation(summary = "api/event/save", description = "행사 추가")
    public ResponseEntity<Event> saveEvent(@RequestBody EventSaveDto eventSaveDto){
        Event event = eventService.saveEvent(eventSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

}
