package com.kafkatest.cab_book_driver.controller;

import com.kafkatest.cab_book_driver.service.CabLocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class CabLocationController {

    @Autowired
    private CabLocationService cabLocationService;

    @PutMapping
    public ResponseEntity<?> updateLocation() throws InterruptedException {
        int range = 10;

        log.info("Starting location update stream for {} iterations", range);

        while (range-- > 0) {
            String location = Math.random() + "," + Math.random();
            cabLocationService.updateLocation(location);

            log.debug("Sent location update: {}", location);

            Thread.sleep(1000); // 1 second interval
        }

        log.info("Completed sending all location updates.");
        return new ResponseEntity<>(Map.of("message", "Location updates completed."), HttpStatus.OK);
    }
}
