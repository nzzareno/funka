package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameApiDTO {
    private Long id;
    private String slug;
    private String name;

    @JsonProperty("name_original")
    private String nameOriginal;

    private String released;

    private Boolean tba;

    @JsonProperty("background_image")
    private String backgroundImage;

    private Double rating;

    @JsonProperty("rating_top")
    private Integer ratingTop;

    @JsonProperty("ratings_count")
    private Integer ratingsCount;

    @JsonProperty("reviews_text_count")
    private Integer reviewsTextCount;

    @JsonProperty("added")
    private Integer added;

    @JsonProperty("added_by_status")
    private AddedByStatusApiDTO addedByStatus;

    private Integer metacritic;

    private Integer playtime;

    @JsonProperty("suggestions_count")
    private Integer suggestionsCount;

    private String updated;

    @JsonProperty("esrb_rating")
    private EsrbRatingApiDTO esrbRating;

    private List<PlatformInfoApiDTO> platforms;

    @JsonProperty("parent_platforms")
    private List<ParentPlatformInfoApiDTO> parentPlatforms;

    private List<GenreApiDTO> genres;

    private List<StoreInfoApiDTO> stores;

    private List<TagApiDTO> tags;

    @JsonProperty("short_screenshots")
    private List<ScreenshotApiDTO> shortScreenshots;

    @JsonProperty("community_rating")
    private Integer communityRating;
}