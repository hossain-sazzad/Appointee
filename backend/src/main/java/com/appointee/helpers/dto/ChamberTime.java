package com.appointee.helpers.dto;

import com.appointee.models.Chamber;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChamberTime {
    Chamber chamber;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;
}
