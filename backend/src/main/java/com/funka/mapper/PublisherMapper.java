package com.funka.mapper;

import com.funka.dto.api.PublisherApiDTO;
import com.funka.dto.api.response.PublisherListApiResponseDTO;
import com.funka.dto.domain.PublisherDTO;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublisherMapper {

    /**
     * Convierte PublisherApiDTO a PublisherDTO
     */
    public PublisherDTO toPublisherDTO(PublisherApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return PublisherDTO.builder()
                .id(apiDTO.getId())
                .name(apiDTO.getName())
                .slug(apiDTO.getSlug())
                .gamesCount(apiDTO.getGamesCount())
                .build();
    }

    /**
     * Convierte lista de PublisherApiDTO a lista de PublisherDTO
     */
    public List<PublisherDTO> toPublisherDTOList(List<PublisherApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toPublisherDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Convierte PublisherListApiResponseDTO a lista de PublisherDTO
     */
    public List<PublisherDTO> toPublisherDTOListFromResponse(PublisherListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return toPublisherDTOList(apiResponse.getResults());
    }
}
