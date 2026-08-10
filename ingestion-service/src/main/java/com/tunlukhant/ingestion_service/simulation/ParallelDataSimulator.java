package com.tunlukhant.ingestion_service.simulation;

import com.tunlukhant.ingestion_service.dto.EnergyUsageDto;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class ParallelDataSimulator implements CommandLineRunner {

    @Value("${simulator.parallel-threads}")
    private int parallelThreads;

    @Value("${simulation.requests-per-interval}")
    private int requestPerInterval;

    @Value("${simulation.endpoint}")
    private String ingestionEndpoint;

    private final ExecutorService executorService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    public ParallelDataSimulator() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("ParallelDataSimulator started....");
        ((ThreadPoolExecutor) executorService).setCorePoolSize(parallelThreads);
    }

//    @Scheduled(fixedRateString = "${simulation.interval-ms}")
    public void sendMockData() {
        int batchSize = requestPerInterval / parallelThreads;
        int remainder = requestPerInterval % parallelThreads;

        for (int i = 0; i < parallelThreads; i++) {
            int requestForThread = batchSize + (i < remainder ? 1 : 0);
            executorService.submit(() -> {
                for (int j = 0; j < requestForThread; j++) {
                    EnergyUsageDto dto = EnergyUsageDto.builder()
                            .deviceId((random.nextLong(200) + 1))
                            .energyConsumed(Math.round(random.nextDouble(10.0) * 100.0) / 100.0)
                            .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())
                            .build();
                    try {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        HttpEntity<EnergyUsageDto> req = new HttpEntity<>(dto, headers);
                        restTemplate.postForEntity(ingestionEndpoint, req, Void.class);
                        log.info("Sent mock data: {}", dto);
                    } catch (Exception e) {
                        log.error("Failed to send data: {}", e.getMessage());
                    }
                }
            });
        }
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
        log.info("ParallelDataSimulator stopped....");
    }
}
