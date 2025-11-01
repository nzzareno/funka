package com.funka.mapper;

import com.funka.dto.api.PlatformInfoApiDTO;
import com.funka.dto.api.RequirementsApiDTO;
import com.funka.dto.domain.RequirementsDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequirementsMapper {

    /**
     * Convierte RequirementsApiDTO a RequirementsDTO
     */
    public RequirementsDTO toRequirementsDTO(RequirementsApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return RequirementsDTO.builder()
                .minimum(apiDTO.getMinimum())
                .recommended(apiDTO.getRecommended())
                .build();
    }

    /**
     * Extrae requirements de la primera plataforma PC encontrada
     */
    public RequirementsDTO toRequirementsDTO(List<PlatformInfoApiDTO> platforms) {
        if (platforms == null) {
            return null;
        }

        // Buscar la plataforma PC
        return platforms.stream()
                .filter(p -> p.getPlatform() != null &&
                        "pc".equalsIgnoreCase(p.getPlatform().getSlug()))
                .findFirst()
                .map(p -> {
                    RequirementsApiDTO req = p.getRequirementsEn() != null
                            ? p.getRequirementsEn()
                            : p.getRequirements();

                    return toRequirementsDTO(req);
                })
                .orElse(null);
    }
}
