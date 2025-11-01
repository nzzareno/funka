package com.funka.controller;

import com.funka.dto.domain.StoreDTO;
import com.funka.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        log.info("GET /api/stores");
        List<StoreDTO> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/pc")
    public ResponseEntity<List<StoreDTO>> getPCStores() {
        log.info("GET /api/stores/pc");
        List<StoreDTO> stores = storeService.getPCStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) {
        log.info("GET /api/stores/{}", id);
        StoreDTO store = storeService.getStoreById(id);
        if (store == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(store);
    }
}