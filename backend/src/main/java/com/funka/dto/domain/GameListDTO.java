package com.funka.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameListDTO {
    private Integer totalCount;
    private Integer page;
    private Integer pageSize;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private List<GameDTO> games;
}
