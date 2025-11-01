package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.StoreListApiResponseDTO;
import com.funka.dto.domain.StoreDTO;
import com.funka.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final RawgApiClient client;
    private final StoreMapper mapper;

    public List<StoreDTO> getAllStores() {
        log.info("Fetching all stores");
        StoreListApiResponseDTO apiResponse = client.getStores();
        return mapper.toStoreDTOListFromResponse(apiResponse);
    }
    public List<StoreDTO> getPCStores() {
        log.info("Fetching PC stores");
        List<StoreDTO> allStores = getAllStores();
        List<Long> pcStoreIds = List.of(1L, 11L, 5L);  // Steam, Epic Games, GOG
        return allStores.stream()
                .filter(store -> pcStoreIds.contains(store.getId()))
                .toList();
    }
    public StoreDTO getStoreById(Long storeId) {
        log.info("Fetching store by ID: {}", storeId);
        List<StoreDTO> allStores = getAllStores();
        return allStores.stream()
                .filter(store -> store.getId().equals(storeId))
                .findFirst()
                .orElse(null);
    }
}