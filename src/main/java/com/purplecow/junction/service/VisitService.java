package com.purplecow.junction.service;

import com.purplecow.junction.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VisitService {
    private final VisitRepository visitRepository;
}
