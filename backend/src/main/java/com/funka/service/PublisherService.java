package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.PublisherListApiResponseDTO;
import com.funka.dto.domain.PublisherDTO;
import com.funka.mapper.PublisherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para gestionar publishers (publicadores) de juegos.
 * Ejemplos de publishers:
 * - Electronic Arts
 * - Ubisoft
 * - Activision
 * - Square Enix
 * - 2K Games
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherService {

    private final RawgApiClient client;
    private final PublisherMapper mapper;

    public List<PublisherDTO> getAllPublishers() {
        log.info("Fetching all publishers");
        PublisherListApiResponseDTO apiResponse = client.getPublishers();
        return mapper.toPublisherDTOListFromResponse(apiResponse);
    }
    public List<PublisherDTO> getPopularPublishers() {
        log.info("Fetching popular publishers");
        List<PublisherDTO> allPublishers = getAllPublishers();
        return allPublishers.stream()
                .sorted((p1, p2) -> Integer.compare(
                        p2.getGamesCount() != null ? p2.getGamesCount() : 0,
                        p1.getGamesCount() != null ? p1.getGamesCount() : 0
                ))
                .limit(50)
                .toList();
    }
    public List<PublisherDTO> searchPublishers(String searchTerm) {
        log.info("Searching publishers with term: '{}'", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllPublishers();
        }
        List<PublisherDTO> allPublishers = getAllPublishers();
        return allPublishers.stream()
                .filter(pub -> pub.getName()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .toList();
    }
    public PublisherDTO getPublisherById(Long publisherId) {
        log.info("Fetching publisher by ID: {}", publisherId);

        List<PublisherDTO> allPublishers = getAllPublishers();

        return allPublishers.stream()
                .filter(pub -> pub.getId().equals(publisherId))
                .findFirst()
                .orElse(null);
    }
}