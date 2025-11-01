package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequirementsApiDTO {
    private String minimum;
    private String recommended;
}