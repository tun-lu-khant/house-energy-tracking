package com.tunlukhant.usage_service.controller;

import com.tunlukhant.usage_service.dto.UsageDto;
import com.tunlukhant.usage_service.service.UsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usages")
@RequiredArgsConstructor
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/{userId}")
    public ResponseEntity<UsageDto> getUserDeviceUsage(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "3") int days
    ) {
        UsageDto usage = usageService.getXDaysUsageForUser(userId, days);
        return ResponseEntity.ok(usage);
    }
}
