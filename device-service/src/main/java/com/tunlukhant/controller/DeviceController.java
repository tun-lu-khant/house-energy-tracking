package com.tunlukhant.controller;

import com.tunlukhant.dto.DeviceDto;
import com.tunlukhant.payload.DeviceRequest;
import com.tunlukhant.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody @Valid DeviceRequest deviceRequest) {
        DeviceDto createdDevice = deviceService.createDevice(deviceRequest);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id) {
        DeviceDto deviceGetById = deviceService.getDeviceById(id);
        return ResponseEntity.ok(deviceGetById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable Long id,
                                                  @RequestBody @Valid DeviceRequest deviceRequest) {
        DeviceDto updatedDevice = deviceService.updateDevice(id, deviceRequest);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
