package com.tunlukhant.kafka.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
public record EnergyUsageEvent (
        Long deviceId,
        double energyConsumed,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Instant timestamp
) {}