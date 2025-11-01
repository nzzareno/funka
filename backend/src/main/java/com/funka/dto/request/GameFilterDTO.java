package com.funka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameFilterDTO {
    private String searchTerm;
    private List<Long> genreIds;
    private List<Long> platformIds;
    private List<Long> storeIds;
    private Double minRating;
    private Double maxRating;
    private Integer minMetacritic;
    private Integer maxMetacritic;
    private LocalDate releaseDateFrom;
    private LocalDate releaseDateTo;
    private String sortBy;
    private String sortDirection;
    private Integer page;
    private Integer size;
}