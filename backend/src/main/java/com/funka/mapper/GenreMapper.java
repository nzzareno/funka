package com.funka.mapper;


import com.funka.dto.api.GenreApiDTO;
import com.funka.dto.api.response.GenreListApiResponseDTO;
import com.funka.dto.domain.GenreDTO;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    /**
     * Convierte GenreApiDTO a GenreDTO
     */
    public GenreDTO toGenreDTO(GenreApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return GenreDTO.builder()
                .id(apiDTO.getId())
                .name(apiDTO.getName())
                .slug(apiDTO.getSlug())
                .image(apiDTO.getImageBackground())
                .gamesCount(apiDTO.getGamesCount())
                .build();
    }

    /**
     * Convierte lista de GenreApiDTO a lista de GenreDTO
     */
    public List<GenreDTO> toGenreDTOList(List<GenreApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toGenreDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Convierte GenreListApiResponseDTO a lista de GenreDTO
     */
    public List<GenreDTO> toGenreDTOListFromResponse(GenreListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return toGenreDTOList(apiResponse.getResults());
    }

    /**
     * Extrae solo los nombres de los géneros
     */
    public List<String> extractGenreNames(List<GenreApiDTO> genres) {
        if (genres == null) {
            return Collections.emptyList();
        }

        return genres.stream()
                .map(GenreApiDTO::getName)
                .collect(Collectors.toList());
    }
}
