package com.funka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameSearchRequestDTO {
    private String search;
    private Integer page;
    private Integer pageSize;
    private String ordering;
    private Long genres;
    private Long platforms;
    private Long stores;
    private Long tags;
    private String dates;
    private Boolean excludeAdditions;
}
