package com.tunlukhant.payload;

import com.tunlukhant.model.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceRequest {

    private String name;

    private DeviceType type;

    private String location;

    private Long userId;
}
