package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetacriticPlatformApiDTO {
    private Integer metascore;
    private String url;

    @JsonProperty("platform")
    private MetacriticPlatformInfoApiDTO platform;
}