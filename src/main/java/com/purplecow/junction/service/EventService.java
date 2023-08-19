package com.purplecow.junction.service;


import com.purplecow.junction.domain.Event;
import com.purplecow.junction.dto.EventSaveDto;
import com.purplecow.junction.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;

    @Transactional
    public Event saveEvent(@RequestBody EventSaveDto eventSaveDto){
        Event event=eventSaveDto.toEntity();
        eventRepository.save(event);
        return event;
    }

    @Transactional
    public Event findById(int idx){
        Event event = eventRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 행사가 없습니다. id="+idx));
        return event;
    }

    //get에서는 참가한 부스 이름 리스트 추가
}
