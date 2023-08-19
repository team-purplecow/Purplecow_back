package com.purplecow.junction.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Position {
    PARTICIPANT,
    SPONSOR,
    HOST
}
