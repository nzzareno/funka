package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreenshotApiDTO {
    private Long id;
    private String image;
    private Integer width;
    private Integer height;
}