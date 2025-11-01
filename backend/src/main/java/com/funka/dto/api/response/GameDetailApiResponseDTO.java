package com.funka.dto.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.funka.dto.api.DeveloperApiDTO;
import com.funka.dto.api.GameApiDTO;
import com.funka.dto.api.MetacriticPlatformApiDTO;
import com.funka.dto.api.PublisherApiDTO;
import com.funka.dto.api.RatingApiDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDetailApiResponseDTO extends GameApiDTO {
    private String description;

    @JsonProperty("description_raw")
    private String descriptionRaw;

    private String website;

    @JsonProperty("reddit_url")
    private String redditUrl;

    @JsonProperty("reddit_name")
    private String redditName;

    @JsonProperty("reddit_description")
    private String redditDescription;

    @JsonProperty("reddit_logo")
    private String redditLogo;

    @JsonProperty("reddit_count")
    private Integer redditCount;

    @JsonProperty("twitch_count")
    private Integer twitchCount;

    @JsonProperty("youtube_count")
    private Integer youtubeCount;

    @JsonProperty("reviews_count")
    private Integer reviewsCount;

    @JsonProperty("saturated_color")
    private String saturatedColor;

    @JsonProperty("dominant_color")
    private String dominantColor;

    @JsonProperty("metacritic_url")
    private String metacriticUrl;

    @JsonProperty("parents_count")
    private Integer parentsCount;

    @JsonProperty("additions_count")
    private Integer additionsCount;

    @JsonProperty("game_series_count")
    private Integer gameSeriesCount;

    @JsonProperty("user_game")
    private String userGame;

    @JsonProperty("reviews_text_count")
    private Integer reviewsTextCount;

    private List<RatingApiDTO> ratings;

    private List<DeveloperApiDTO> developers;

    private List<PublisherApiDTO> publishers;

    @JsonProperty("alternative_names")
    private List<String> alternativeNames;

    @JsonProperty("metacritic_platforms")
    private List<MetacriticPlatformApiDTO> metacriticPlatforms;
}
