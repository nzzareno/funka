package com.funka.controller;

import com.funka.dto.domain.TagDTO;
import com.funka.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        log.info("GET /api/tags");
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<TagDTO>> getPopularTags() {
        log.info("GET /api/tags/popular");
        List<TagDTO> tags = tagService.getPopularTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TagDTO>> searchTags(@RequestParam String query) {
        log.info("GET /api/tags/search - Query: '{}'", query);
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<TagDTO> tags = tagService.searchTags(query);
        return ResponseEntity.ok(tags);
    }
}