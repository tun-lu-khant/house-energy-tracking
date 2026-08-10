package com.tunlukhant.ingestion_service.controller;

import com.tunlukhant.ingestion_service.dto.EnergyUsageDto;
import com.tunlukhant.ingestion_service.service.IngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ingestions")
@RequiredArgsConstructor
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto) {
        ingestionService.ingestEnergyUsage(usageDto);
    }

}
