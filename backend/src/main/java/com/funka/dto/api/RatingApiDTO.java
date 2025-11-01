package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingApiDTO {
    private Integer id;
    private String title;
    private Integer count;
    private Double percent;
}