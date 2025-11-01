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
public class GameDTO {
    private Long id;
    private String slug;
    private String name;
    private String image;
    private LocalDate releaseDate;
    private Double rating;
    private Integer metacritic;
    private Integer playtime;
    private String esrbRating;
    private List<String> platforms;
    private List<String> genres;
    private List<String> tags;
    private List<String> stores;
    private String shortDescription;
}
