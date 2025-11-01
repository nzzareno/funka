package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.GenreListApiResponseDTO;
import com.funka.dto.domain.GenreDTO;
import com.funka.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService {

    private final RawgApiClient client;
    private final GenreMapper mapper;

    public List<GenreDTO> getAllGenres() {
        log.info("Fetching all genres");
        GenreListApiResponseDTO apiResponse = client.getGenres();
        return mapper.toGenreDTOListFromResponse(apiResponse);
    }

    public GenreDTO getGenreById(Long genreId) {
        log.info("Fetching genre by ID: {}", genreId);
        List<GenreDTO> allGenres = getAllGenres();
        return allGenres.stream()
                .filter(genre -> genre.getId().equals(genreId))
                .findFirst()
                .orElse(null);
    }
}