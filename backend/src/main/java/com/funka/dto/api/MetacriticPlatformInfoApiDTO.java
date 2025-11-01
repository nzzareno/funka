package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetacriticPlatformInfoApiDTO {
    private Integer platform;
    private String name;
    private String slug;
}