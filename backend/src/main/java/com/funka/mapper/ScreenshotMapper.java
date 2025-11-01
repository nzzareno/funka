package com.funka.mapper;

import com.funka.dto.api.ScreenshotApiDTO;
import com.funka.dto.api.response.ScreenshotListApiResponseDTO;
import com.funka.dto.domain.ScreenshotDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScreenshotMapper {

    /**
     * Convierte ScreenshotApiDTO a ScreenshotDTO
     */
    public ScreenshotDTO toScreenshotDTO(ScreenshotApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return ScreenshotDTO.builder()
                .id(apiDTO.getId())
                .image(apiDTO.getImage())
                .width(apiDTO.getWidth())
                .height(apiDTO.getHeight())
                .build();
    }

    /**
     * Convierte lista de ScreenshotApiDTO a lista de ScreenshotDTO
     */
    public List<ScreenshotDTO> toScreenshotDTOList(List<ScreenshotApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toScreenshotDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Convierte ScreenshotListApiResponseDTO a lista de ScreenshotDTO
     */
    public List<ScreenshotDTO> toScreenshotDTOListFromResponse(ScreenshotListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return toScreenshotDTOList(apiResponse.getResults());
    }
}
