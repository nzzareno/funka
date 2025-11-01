package com.funka.mapper;

import com.funka.dto.api.TagApiDTO;
import com.funka.dto.api.response.TagListApiResponseDTO;
import com.funka.dto.domain.TagDTO;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {

    /**
     * Convierte TagApiDTO a TagDTO
     */
    public TagDTO toTagDTO(TagApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return TagDTO.builder()
                .id(apiDTO.getId())
                .name(apiDTO.getName())
                .slug(apiDTO.getSlug())
                .build();
    }

    /**
     * Convierte lista de TagApiDTO a lista de TagDTO
     */
    public List<TagDTO> toTagDTOList(List<TagApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toTagDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Convierte TagListApiResponseDTO a lista de TagDTO
     */
    public List<TagDTO> toTagDTOListFromResponse(TagListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return toTagDTOList(apiResponse.getResults());
    }

    /**
     * Extrae solo los nombres de los tags
     */
    public List<String> extractTagNames(List<TagApiDTO> tags) {
        if (tags == null) {
            return Collections.emptyList();
        }

        return tags.stream()
                .map(TagApiDTO::getName)
                .collect(Collectors.toList());
    }
}
