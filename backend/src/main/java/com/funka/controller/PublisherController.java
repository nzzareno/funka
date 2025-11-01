package com.funka.controller;

import com.funka.dto.domain.PublisherDTO;
import com.funka.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        log.info("GET /api/publishers");
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<PublisherDTO>> getPopularPublishers() {
        log.info("GET /api/publishers/popular");
        List<PublisherDTO> publishers = publisherService.getPopularPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PublisherDTO>> searchPublishers(@RequestParam String query) {
        log.info("GET /api/publishers/search - Query: '{}'", query);
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<PublisherDTO> publishers = publisherService.searchPublishers(query);
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable Long id) {
        log.info("GET /api/publishers/{}", id);
        PublisherDTO publisher = publisherService.getPublisherById(id);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(publisher);
    }
}