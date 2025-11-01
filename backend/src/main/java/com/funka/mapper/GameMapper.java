package com.funka.mapper;

import com.funka.dto.api.GameApiDTO;
import com.funka.dto.api.response.GameDetailApiResponseDTO;
import com.funka.dto.api.response.GameListApiResponseDTO;
import com.funka.dto.domain.GameDTO;
import com.funka.dto.domain.GameDetailDTO;
import com.funka.dto.domain.GameListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameMapper {

    private final PlatformMapper platformMapper;
    private final GenreMapper genreMapper;
    private final TagMapper tagMapper;
    private final StoreMapper storeMapper;
    private final DeveloperMapper developerMapper;
    private final PublisherMapper publisherMapper;
    private final ScreenshotMapper screenshotMapper;
    private final RequirementsMapper requirementsMapper;

    /**
     * Convierte GameApiDTO (de lista) a GameDTO (dominio simplificado)
     */
    public GameDTO toGameDTO(GameApiDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return GameDTO.builder()
                .id(apiDTO.getId())
                .slug(apiDTO.getSlug())
                .name(apiDTO.getName())
                .image(apiDTO.getBackgroundImage())
                .releaseDate(parseDate(apiDTO.getReleased()))
                .rating(apiDTO.getRating())
                .metacritic(apiDTO.getMetacritic())
                .playtime(apiDTO.getPlaytime())
                .esrbRating(apiDTO.getEsrbRating() != null ? apiDTO.getEsrbRating().getName() : null)
                .platforms(platformMapper.extractPlatformNames(apiDTO.getPlatforms()))
                .genres(genreMapper.extractGenreNames(apiDTO.getGenres()))
                .tags(tagMapper.extractTagNames(apiDTO.getTags()))
                .stores(storeMapper.extractStoreNames(apiDTO.getStores()))
                .build();
    }

    /**
     * Convierte GameDetailApiResponseDTO a GameDetailDTO (dominio completo)
     */
    public GameDetailDTO toGameDetailDTO(GameDetailApiResponseDTO apiDTO) {
        if (apiDTO == null) {
            return null;
        }

        return GameDetailDTO.builder()
                .id(apiDTO.getId())
                .slug(apiDTO.getSlug())
                .name(apiDTO.getName())
                .nameOriginal(apiDTO.getNameOriginal())
                .image(apiDTO.getBackgroundImage())
                .releaseDate(parseDate(apiDTO.getReleased()))
                .rating(apiDTO.getRating())
                .ratingsCount(apiDTO.getRatingsCount())
                .metacritic(apiDTO.getMetacritic())
                .playtime(apiDTO.getPlaytime())
                .description(apiDTO.getDescriptionRaw())
                .website(apiDTO.getWebsite())
                .esrbRating(apiDTO.getEsrbRating() != null ? apiDTO.getEsrbRating().getName() : null)
                .platforms(platformMapper.toPlatformDTOList(apiDTO.getPlatforms()))
                .genres(genreMapper.toGenreDTOList(apiDTO.getGenres()))
                .tags(tagMapper.toTagDTOList(apiDTO.getTags()))
                .stores(storeMapper.toStoreDTOList(apiDTO.getStores()))
                .developers(developerMapper.toDeveloperDTOList(apiDTO.getDevelopers()))
                .publishers(publisherMapper.toPublisherDTOList(apiDTO.getPublishers()))
                .screenshots(screenshotMapper.toScreenshotDTOList(apiDTO.getShortScreenshots()))
                .requirements(requirementsMapper.toRequirementsDTO(apiDTO.getPlatforms()))
                .build();
    }

    /**
     * Convierte GameListApiResponseDTO a GameListDTO
     */
    public GameListDTO toGameListDTO(GameListApiResponseDTO apiResponse) {
        if (apiResponse == null) {
            return null;
        }

        List<GameDTO> games = apiResponse.getResults() != null
                ? apiResponse.getResults().stream()
                .map(this::toGameDTO)
                .filter(game -> game != null && game.getImage() != null && !game.getImage().trim().isEmpty())
                .collect(Collectors.toList())
                : Collections.emptyList();

        // Calcular página actual basado en la URL de next/previous si está disponible
        Integer currentPage = extractPageFromUrl(apiResponse.getPrevious()) != null ? extractPageFromUrl(apiResponse.getPrevious()) + 1 : 1;
        
        return GameListDTO.builder()
                .totalCount(apiResponse.getCount())
                .page(currentPage)
                .pageSize(20) // Tamaño de página por defecto que usamos en el servicio
                .hasNext(apiResponse.getNext() != null)
                .hasPrevious(apiResponse.getPrevious() != null)
                .games(games)
                .build();
    }

    /**
     * Convierte lista de GameApiDTO a lista de GameDTO
     */
    public List<GameDTO> toGameDTOList(List<GameApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toGameDTO)
                .collect(Collectors.toList());
    }

    /**
     * Parsea string de fecha a LocalDate
     */
    private LocalDate parseDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extrae el número de página de una URL de la API
     */
    private Integer extractPageFromUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        try {
            String[] parts = url.split("[?&]");
            for (String part : parts) {
                if (part.startsWith("page=")) {
                    return Integer.valueOf(part.substring(5));
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid page number in URL: " + url, e);
        }
        
        return null;
    }
}