package com.funka.mapper;

import com.funka.dto.api.PlatformApiDTO;
import com.funka.dto.api.PlatformInfoApiDTO;
import com.funka.dto.domain.PlatformDTO;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlatformMapper {

    /**
     * Convierte PlatformInfoApiDTO a PlatformDTO
     */
    public PlatformDTO toPlatformDTO(PlatformInfoApiDTO apiDTO) {
        if (apiDTO == null || apiDTO.getPlatform() == null) {
            return null;
        }

        PlatformApiDTO platform = apiDTO.getPlatform();

        return PlatformDTO.builder()
                .id(platform.getId())
                .name(platform.getName())
                .slug(platform.getSlug())
                .releasedAt(apiDTO.getReleasedAt())
                .build();
    }

    /**
     * Convierte lista de PlatformInfoApiDTO a lista de PlatformDTO
     */
    public List<PlatformDTO> toPlatformDTOList(List<PlatformInfoApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toPlatformDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Extrae solo los nombres de las plataformas
     */
    public List<String> extractPlatformNames(List<PlatformInfoApiDTO> platforms) {
        if (platforms == null) {
            return Collections.emptyList();
        }

        return platforms.stream()
                .filter(p -> p.getPlatform() != null)
                .map(p -> p.getPlatform().getName())
                .collect(Collectors.toList());
    }
}