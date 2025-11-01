package com.funka.controller;

import com.funka.dto.domain.DeveloperDTO;
import com.funka.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAllDevelopers() {
        log.info("GET /api/developers");
        List<DeveloperDTO> developers = developerService.getAllDevelopers();
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<DeveloperDTO>> getPopularDevelopers() {
        log.info("GET /api/developers/popular");
        List<DeveloperDTO> developers = developerService.getPopularDevelopers();
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DeveloperDTO>> searchDevelopers(@RequestParam String query) {
        log.info("GET /api/developers/search - Query: '{}'", query);
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<DeveloperDTO> developers = developerService.searchDevelopers(query);
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getDeveloperById(@PathVariable Long id) {
        log.info("GET /api/developers/{}", id);
        DeveloperDTO developer = developerService.getDeveloperById(id);
        if (developer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(developer);
    }
}