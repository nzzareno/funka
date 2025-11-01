package com.funka.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {
    private Long id;
    private String name;
    private String slug;
    private String image;
    private Integer gamesCount;
}
