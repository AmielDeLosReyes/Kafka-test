package com.kafkatest.cab_book_driver.service;

import com.kafkatest.cab_book_driver.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CabLocationService {

    /**
     * Use Kafka Template
     * - Helps us create the template and store the data to your Apache Kafka cluster.
     * - Key-Value combination
     * - Which topic we need to send the data
     * - Mostly configuration
     *
     * Here, our combination is:
     *      Key: String
     *      Value: Object
     */
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public boolean updateLocation(String location) {
        // Use kafkaTemplate then send the data (location) to Kafka Topic (cab-location)
        log.info("Publishing cab location to Kafka topic: {}, data: {}", AppConstant.CAB_LOCATION, location);
        kafkaTemplate.send(AppConstant.CAB_LOCATION, location);
        return true;
    }
}
