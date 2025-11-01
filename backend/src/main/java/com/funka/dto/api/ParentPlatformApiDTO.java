package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentPlatformApiDTO {
    private Long id;
    private String name;
    private String slug;
}