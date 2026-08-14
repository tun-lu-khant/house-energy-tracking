package com.tunlukhant.usage_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    Long id;
    String name;
    String type;
    String location;
    Long userId;
    Double energyConsumed;
}
