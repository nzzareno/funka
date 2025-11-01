package com.funka.client;

import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.funka.config.RawgApiConfig;
import com.funka.dto.api.response.DeveloperListApiResponseDTO;
import com.funka.dto.api.response.GameDetailApiResponseDTO;
import com.funka.dto.api.response.GameListApiResponseDTO;
import com.funka.dto.api.response.GenreListApiResponseDTO;
import com.funka.dto.api.response.PlatformListApiResponseDTO;
import com.funka.dto.api.response.PublisherListApiResponseDTO;
import com.funka.dto.api.response.ScreenshotListApiResponseDTO;
import com.funka.dto.api.response.StoreListApiResponseDTO;
import com.funka.dto.api.response.TagListApiResponseDTO;
import com.funka.exception.RawgApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RawgApiClient {

    private final RestTemplate restTemplate;
    private final RawgApiConfig config;

    // ==================== GAMES ====================

    public GameListApiResponseDTO getGames(Integer page, Integer pageSize) {
        String url = config.buildUrl("/games",
                "page", page != null ? page.toString() : "1",
                "page_size", pageSize != null ? pageSize.toString() : config.getPageSize().toString()
        );

        log.info("Fetching games - Page: {}, PageSize: {}", page, pageSize);

        return executeRequest(url, GameListApiResponseDTO.class);
    }

    public GameListApiResponseDTO searchGames(String search, Integer page, Integer pageSize) {
        if (search == null || search.trim().isEmpty()) {
            return getGames(page, pageSize);
        }

        String url = config.buildUrl("/games",
                "search", search,
                "page", page != null ? page.toString() : "1",
                "page_size", pageSize != null ? pageSize.toString() : config.getPageSize().toString()
        );

        log.info("Searching games - Query: '{}', Page: {}", search, page);

        return executeRequest(url, GameListApiResponseDTO.class);
    }

    public GameDetailApiResponseDTO getGameById(Long gameId) {
        String url = config.buildUrl("/games/" + gameId);

        log.info("Fetching game details - ID: {}", gameId);

        return executeRequest(url, GameDetailApiResponseDTO.class);
    }

    public ScreenshotListApiResponseDTO getGameScreenshots(Long gameId) {
        String url = config.buildUrl("/games/" + gameId + "/screenshots");

        log.info("Fetching screenshots for game - ID: {}", gameId);

        return executeRequest(url, ScreenshotListApiResponseDTO.class);
    }

    public GameListApiResponseDTO searchGamesWithFilters(String search, String genres, String platforms, String stores, String ordering, Integer page, Integer pageSize) {

        StringBuilder params = new StringBuilder();

        if (search != null && !search.trim().isEmpty()) {
            params.append("search=").append(search).append("&");
        }
        if (genres != null && !genres.trim().isEmpty()) {
            params.append("genres=").append(genres).append("&");
        }
        if (platforms != null && !platforms.trim().isEmpty()) {
            params.append("platforms=").append(platforms).append("&");
        }
        if (stores != null && !stores.trim().isEmpty()) {
            params.append("stores=").append(stores).append("&");
        }
        if (ordering != null && !ordering.trim().isEmpty()) {
            params.append("ordering=").append(ordering).append("&");
        }

        params.append("page=").append(page != null ? page : 1).append("&");
        params.append("page_size=").append(pageSize != null ? pageSize : config.getPageSize()).append("&");
        params.append("exclude_additions=true"); // Excluir DLCs y contenido adicional que suele no tener imágenes

        String url = config.buildUrl("/games", params.toString());

        log.info("Searching games with filters");

        return executeRequest(url, GameListApiResponseDTO.class);
    }

    // ==================== GENRES ====================

    public GenreListApiResponseDTO getGenres() {
        String url = config.buildUrl("/genres");
        log.info("Fetching all genres");
        return executeRequest(url, GenreListApiResponseDTO.class);
    }

    // ==================== PLATFORMS ====================

    public PlatformListApiResponseDTO getPlatforms() {
        String url = config.buildUrl("/platforms");
        log.info("Fetching all platforms");
        return executeRequest(url, PlatformListApiResponseDTO.class);
    }

    // ==================== STORES ====================

    public StoreListApiResponseDTO getStores() {
        String url = config.buildUrl("/stores");
        log.info("Fetching all stores");
        return executeRequest(url, StoreListApiResponseDTO.class);
    }

    // ==================== TAGS ====================

    public TagListApiResponseDTO getTags() {
        String url = config.buildUrl("/tags");
        log.info("Fetching all tags");
        return executeRequest(url, TagListApiResponseDTO.class);
    }

    // ==================== DEVELOPERS ====================

    public DeveloperListApiResponseDTO getDevelopers() {
        String url = config.buildUrl("/developers");
        log.info("Fetching all developers");
        return executeRequest(url, DeveloperListApiResponseDTO.class);
    }

    // ==================== PUBLISHERS ====================

    public PublisherListApiResponseDTO getPublishers() {
        String url = config.buildUrl("/publishers");
        log.info("Fetching all publishers");
        return executeRequest(url, PublisherListApiResponseDTO.class);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private <T> T executeRequest(String url, Class<T> responseType) {
        try {
            log.debug("Calling RAWG API: {}", sanitizeUrl(url));

            T response = restTemplate.getForObject(url, responseType);

            if (response == null) {
                throw new RawgApiException("Empty response from RAWG API", HttpStatus.NO_CONTENT);
            }

            return response;

        } catch (HttpClientErrorException e) {
            log.error("Client error calling RAWG API: {} - {}", e.getStatusCode(), e.getMessage());
            handleClientError(e);
            throw new RawgApiException("Error calling RAWG API: " + e.getMessage(), (HttpStatus) e.getStatusCode());

        } catch (HttpServerErrorException e) {
            log.error("Server error calling RAWG API: {} - {}", e.getStatusCode(), e.getMessage());
            throw new RawgApiException("RAWG API server error", HttpStatus.SERVICE_UNAVAILABLE);

        } catch (RawgApiException | RestClientException e) {
            log.error("Unexpected error calling RAWG API: {}", e.getMessage(), e);
            throw new RawgApiException("Unexpected error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void handleClientError(HttpClientErrorException e) {
        switch (e.getStatusCode()) {
            case NOT_FOUND -> log.warn("Resource not found in RAWG API");
            case UNAUTHORIZED -> log.error("Unauthorized - Check your RAWG API key");
            case FORBIDDEN -> log.error("Forbidden - API key may be invalid or rate limit exceeded");
            case TOO_MANY_REQUESTS -> log.error("Rate limit exceeded - Too many requests to RAWG API");
            default -> log.error("Client error: {}", e.getStatusCode());
        }
    }

    private String sanitizeUrl(String url) {
        return url.replaceAll("key=[^&]+", "key=***");
    }
}