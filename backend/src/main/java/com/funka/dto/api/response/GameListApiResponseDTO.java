package com.funka.dto.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.funka.dto.api.GameApiDTO;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameListApiResponseDTO {
    private Integer count;
    private String next;
    private String previous;
    private List<GameApiDTO> results;

    @JsonProperty("user_platforms")
    private Boolean userPlatforms;

}
