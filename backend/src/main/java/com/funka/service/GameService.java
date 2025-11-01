package com.funka.service;

import com.funka.client.RawgApiClient;
import com.funka.dto.api.response.GameDetailApiResponseDTO;
import com.funka.dto.api.response.GameListApiResponseDTO;
import com.funka.dto.api.response.ScreenshotListApiResponseDTO;
import com.funka.dto.domain.GameDetailDTO;
import com.funka.dto.domain.GameListDTO;
import com.funka.dto.domain.ScreenshotDTO;
import com.funka.mapper.GameMapper;
import com.funka.mapper.ScreenshotMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {

    private final RawgApiClient client;
    private final GameMapper mapper;
    private final ScreenshotMapper screenshotMapper;
    private static final String PC_PLATFORM_ID = "4";

    public GameListDTO getGames(Integer page) {
        log.info("Getting PC games - Page: {}", page);

        // 1. Llamar al client con filtro de PC
        GameListApiResponseDTO apiResponse = client.searchGamesWithFilters(
                null,              // sin búsqueda
                null,              // sin filtro de géneros
                PC_PLATFORM_ID,    // SOLO PC (plataforma 4)
                null,              // sin filtro de tiendas
                "-released",       // ordenar por fecha (más recientes primero)
                page != null ? page : 1,
                20                 // 20 juegos por página
        );

        // 2. Mapear de API DTO a Domain DTO
        return mapper.toGameListDTO(apiResponse);
    }
    public GameListDTO searchGames(String search, Integer page) {
        log.info("Searching PC games - Query: '{}', Page: {}", search, page);

        // 1. Llamar al client con búsqueda y filtro de PC
        GameListApiResponseDTO apiResponse = client.searchGamesWithFilters(
                search,            // término de búsqueda
                null,              // sin filtro de géneros
                PC_PLATFORM_ID,    // SOLO PC
                null,              // sin filtro de tiendas
                "-rating",         // ordenar por rating (mejores primero)
                page != null ? page : 1,
                20
        );

        // 2. Mapear y devolver
        return mapper.toGameListDTO(apiResponse);
    }
    public GameListDTO searchGamesAdvanced(
            String search,
            String genreIds,
            String storeIds,
            String ordering,
            Integer page) {

        log.info("Advanced search - Search: '{}', Genres: {}, Stores: {}, Ordering: {}",
                search, genreIds, storeIds, ordering);

        // 1. Llamar al client con todos los filtros
        GameListApiResponseDTO apiResponse = client.searchGamesWithFilters(
                search,
                genreIds,
                PC_PLATFORM_ID,    // SIEMPRE PC
                storeIds,
                ordering != null ? ordering : "-rating",  // Por defecto: mejor rating
                page != null ? page : 1,
                20
        );

        // 2. Mapear y devolver
        return mapper.toGameListDTO(apiResponse);
    }
    public GameListDTO getGamesByGenre(Long genreId, Integer page) {
        log.info("Getting PC games by genre - GenreId: {}, Page: {}", genreId, page);

        // 1. Llamar al client filtrando por género Y plataforma PC
        GameListApiResponseDTO apiResponse = client.searchGamesWithFilters(
                null,                      // sin búsqueda
                genreId.toString(),        // filtrar por género
                PC_PLATFORM_ID,            // SOLO PC
                null,                      // sin filtro de tiendas
                "-rating",                 // ordenar por rating
                page != null ? page : 1,
                20
        );

        // 2. Mapear y devolver
        return mapper.toGameListDTO(apiResponse);
    }
    public GameDetailDTO getGameById(Long gameId) {
        log.info("Getting game details - ID: {}", gameId);

        // 1. Llamar al client
        GameDetailApiResponseDTO apiResponse = client.getGameById(gameId);

        // 2. Mapear y devolver
        return mapper.toGameDetailDTO(apiResponse);
    }
    public List<ScreenshotDTO> getGameScreenshots(Long gameId) {
        log.info("Getting screenshots for game - ID: {}", gameId);

        // 1. Llamar al client
        ScreenshotListApiResponseDTO apiResponse = client.getGameScreenshots(gameId);

        // 2. Mapear
        return screenshotMapper.toScreenshotDTOList(apiResponse.getResults());
    }
}
