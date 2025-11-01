package com.funka.mapper;

import com.funka.dto.api.DeveloperApiDTO;
import com.funka.dto.api.response.DeveloperListApiResponseDTO;
import com.funka.dto.domain.DeveloperDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DeveloperMapper {

    /**
     * Convierte DeveloperApiDTO a DeveloperDTO
     */
    public DeveloperDTO toDeveloperDTO(DeveloperApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return DeveloperDTO.builder()
                .id(apiDTO.getId())
                .name(apiDTO.getName())
                .slug(apiDTO.getSlug())
                .gamesCount(apiDTO.getGamesCount())
                .build();
    }

    /**
     * Convierte lista de DeveloperApiDTO a lista de DeveloperDTO
     */
    public List<DeveloperDTO> toDeveloperDTOList(List<DeveloperApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toDeveloperDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Convierte DeveloperListApiResponseDTO a lista de DeveloperDTO
     */
    public List<DeveloperDTO> toDeveloperDTOListFromResponse(DeveloperListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return toDeveloperDTOList(apiResponse.getResults());
    }
}