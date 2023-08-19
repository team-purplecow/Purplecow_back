package com.purplecow.junction.service;

import com.purplecow.junction.domain.Event;
import com.purplecow.junction.domain.Visit;
import com.purplecow.junction.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
    public List<Visit> findByEvent(Event event) {
        return visitRepository.findByEvent(event);
    }
}
