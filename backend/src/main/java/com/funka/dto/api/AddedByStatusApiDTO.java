package com.funka.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddedByStatusApiDTO {
    private Integer yet;
    private Integer owned;
    private Integer beaten;
    private Integer toplay;
    private Integer dropped;
    private Integer playing;
}