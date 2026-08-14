package com.tunlukhant.service;

import com.tunlukhant.dto.DeviceDto;
import com.tunlukhant.entity.Device;
import com.tunlukhant.exception.DeviceNotFoundException;
import com.tunlukhant.payload.DeviceRequest;
import com.tunlukhant.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceDto getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(
                        () -> new DeviceNotFoundException("No device found with id " + id)
                );
        return toDto(device);
    }

    public DeviceDto createDevice(DeviceRequest req) {
        Device device = Device.builder()
                .name(req.getName())
                .type(req.getType())
                .location(req.getLocation())
                .userId(req.getUserId())
                .build();
        final Device savedDevice = deviceRepository.save(device);
        return toDto(savedDevice);
    }

    public DeviceDto updateDevice(Long id, DeviceRequest req) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(
                        () -> new DeviceNotFoundException("No device found with id " + id)
                );
        device.setName(req.getName());
        device.setType(req.getType());
        device.setLocation(req.getLocation());
        device.setUserId(req.getUserId());
        return toDto(deviceRepository.save(device));
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException("No device found with id " + id);
        }
        deviceRepository.deleteById(id);
    }

    private DeviceDto toDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }

    public List<DeviceDto> getAllDevicesForUser(Long userId) {
        return deviceRepository.findAllByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
