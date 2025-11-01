package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.TagListApiResponseDTO;
import com.funka.dto.domain.TagDTO;
import com.funka.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestionar tags (etiquetas) de juegos.
 * Ejemplos de tags:
 * - Singleplayer
 * - Multiplayer
 * - Co-op
 * - Open World
 * - First-Person
 * - Third-Person
 * - Survival
 * - Horror
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final RawgApiClient client;
    private final TagMapper mapper;

    public List<TagDTO> getAllTags() {
        log.info("Fetching all tags");
        TagListApiResponseDTO apiResponse = client.getTags();
        return mapper.toTagDTOListFromResponse(apiResponse);
    }
    public List<TagDTO> getPopularTags() {
        log.info("Fetching popular tags");
        List<TagDTO> allTags = getAllTags();
        return allTags.stream()
                .limit(20)
                .toList();
    }
    public List<TagDTO> searchTags(String searchTerm) {
        log.info("Searching tags with term: '{}'", searchTerm);
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllTags();
        }
        List<TagDTO> allTags = getAllTags();
        return allTags.stream()
                .filter(tag -> tag.getName()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .toList();
    }
}