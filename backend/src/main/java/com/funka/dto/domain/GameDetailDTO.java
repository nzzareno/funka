package com.funka.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDetailDTO {
    private Long id;
    private String slug;
    private String name;
    private String nameOriginal;
    private String image;
    private LocalDate releaseDate;
    private Double rating;
    private Integer ratingsCount;
    private Integer metacritic;
    private Integer playtime;
    private String description;
    private String website;
    private String esrbRating;

    private List<PlatformDTO> platforms;
    private List<GenreDTO> genres;
    private List<TagDTO> tags;
    private List<StoreDTO> stores;
    private List<DeveloperDTO> developers;
    private List<PublisherDTO> publishers;
    private List<ScreenshotDTO> screenshots;
    private RequirementsDTO requirements;
}