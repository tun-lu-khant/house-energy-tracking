package com.tunlukhant.usage_service.config;

import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.influxdb.client.InfluxDBClient;

@Configuration
public class InflexDBConfig {

    @Value("${influx.url}")
    private String influxUrl;

    @Value("${influx.token}")
    private String influxToken;

    @Value("${influx.org}")
    private String influxOrg;

    @Bean
    public InfluxDBClient inflexDBClient() {
        return InfluxDBClientFactory.create(
                influxUrl, influxToken.toCharArray(), influxOrg);
    }
}
