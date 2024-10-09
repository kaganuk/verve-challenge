package com.kaganuk.vervechallenge.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ConcurrentHashMap;

@Controller("/api/verve")
@Slf4j
public class VerveController {
    private ConcurrentHashMap<Integer, Boolean> uniqueRequests = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/accept")
    public ResponseEntity<String> acceptRequest(@RequestParam int id, @RequestParam(required = false) String endpoint) {
        try {
            // Deduplicate request using Redis
            //Boolean isNew = redisTemplate.opsForValue().setIfAbsent("request:" + id, "true");
            if (isNew != null && isNew) {
                uniqueRequests.put(id, true);
            }

            // Process external endpoint if provided
            if (endpoint != null) {
                // Placeholder for sending GET or POST request to the provided endpoint
            }

            return ResponseEntity.ok("ok");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("failed");
        }
    }

    @Scheduled(fixedRate = 60000)
    public void logUniqueRequestCount() {
        int uniqueCount = uniqueRequests.size();
        log.info("Unique request count in the last minute: {}", uniqueCount);
        uniqueRequests.clear();
    }
}
