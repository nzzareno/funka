package com.funka.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenshotDTO {
    private Long id;
    private String image;
    private Integer width;
    private Integer height;
}