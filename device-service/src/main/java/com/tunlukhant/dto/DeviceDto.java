package com.tunlukhant.dto;

import com.tunlukhant.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {

    private Long id;

    private String name;

    private DeviceType type;

    private String location;

    private Long userId;

}
