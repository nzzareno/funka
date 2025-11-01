package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreApiDTO {
    private Long id;
    private String name;
    private String slug;

    @JsonProperty("games_count")
    private Integer gamesCount;

    @JsonProperty("image_background")
    private String imageBackground;
}