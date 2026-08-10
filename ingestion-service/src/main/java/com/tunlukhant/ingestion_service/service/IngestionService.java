package com.tunlukhant.ingestion_service.service;

import com.tunlukhant.ingestion_service.dto.EnergyUsageDto;
import com.tunlukhant.kafka.event.EnergyUsageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestionService {

//    private final IngestionRepository ingestionRepository;
    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public void ingestEnergyUsage(EnergyUsageDto usageDto) {
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .energyConsumed(usageDto.energyConsumed())
                .deviceId(usageDto.deviceId())
                .timestamp(usageDto.timestamp())
                .build();

        // Send to Kafka Topic
        kafkaTemplate.send("energy-usage", event);
        log.info("Ingested Energy Usage Event: {}", event);
    }

}
