package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformInfoApiDTO {
    private PlatformApiDTO platform;

    @JsonProperty("released_at")
    private String releasedAt;

    @JsonProperty("requirements")
    private RequirementsApiDTO requirements;

    @JsonProperty("requirements_en")
    private RequirementsApiDTO requirementsEn;

    @JsonProperty("requirements_ru")
    private RequirementsApiDTO requirementsRu;
}
