package com.funka.dto.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funka.dto.api.GenreApiDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreListApiResponseDTO {
    private Integer count;
    private String next;
    private String previous;
    private List<GenreApiDTO> results;
}
