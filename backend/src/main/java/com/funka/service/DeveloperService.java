package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.DeveloperListApiResponseDTO;
import com.funka.dto.domain.DeveloperDTO;
import com.funka.mapper.DeveloperMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar desarrolladores de juegos.
 * Ejemplos de desarrolladores:
 * - Rockstar Games
 * - CD Projekt Red
 * - Bethesda Game Studios
 * - Valve
 * - FromSoftware
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final RawgApiClient client;
    private final DeveloperMapper mapper;

    public List<DeveloperDTO> getAllDevelopers() {
        log.info("Fetching all developers");
        DeveloperListApiResponseDTO apiResponse = client.getDevelopers();
        return mapper.toDeveloperDTOListFromResponse(apiResponse);
    }
    public List<DeveloperDTO> getPopularDevelopers() {
        log.info("Fetching popular developers");
        List<DeveloperDTO> allDevelopers = getAllDevelopers();
        return allDevelopers.stream()
                .sorted((d1, d2) -> Integer.compare(
                        d2.getGamesCount() != null ? d2.getGamesCount() : 0,
                        d1.getGamesCount() != null ? d1.getGamesCount() : 0
                ))
                .limit(50)
                .toList();
    }
    public List<DeveloperDTO> searchDevelopers(String searchTerm) {
        log.info("Searching developers with term: '{}'", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllDevelopers();
        }
        List<DeveloperDTO> allDevelopers = getAllDevelopers();
        return allDevelopers.stream()
                .filter(dev -> dev.getName()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .toList();
    }
    public DeveloperDTO getDeveloperById(Long developerId) {
        log.info("Fetching developer by ID: {}", developerId);

        List<DeveloperDTO> allDevelopers = getAllDevelopers();

        return allDevelopers.stream()
                .filter(dev -> dev.getId().equals(developerId))
                .findFirst()
                .orElse(null);
    }
}