package com.funka.dto.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.funka.dto.api.TagApiDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagListApiResponseDTO {
    private Integer count;
    private String next;
    private String previous;
    private List<TagApiDTO> results;
}
