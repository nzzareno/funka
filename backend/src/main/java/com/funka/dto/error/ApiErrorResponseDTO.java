package com.funka.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponseDTO {
    private String error;
    private String detail;
    private Integer status;
}
