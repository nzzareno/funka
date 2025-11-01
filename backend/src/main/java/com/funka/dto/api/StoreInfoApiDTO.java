package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoreInfoApiDTO {
    private Long id;
    private String url;
    private StoreApiDTO store;
}