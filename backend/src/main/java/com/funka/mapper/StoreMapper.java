package com.funka.mapper;

import com.funka.dto.api.StoreInfoApiDTO;
import com.funka.dto.api.response.StoreListApiResponseDTO;
import com.funka.dto.domain.StoreDTO;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    /**
     * Convierte StoreInfoApiDTO a StoreDTO
     */
    public StoreDTO toStoreDTO(StoreInfoApiDTO apiDTO) {
        if (apiDTO == null || apiDTO.getStore() == null) {
            return null;
        }

        return StoreDTO.builder()
                .id(apiDTO.getStore().getId())
                .name(apiDTO.getStore().getName())
                .slug(apiDTO.getStore().getSlug())
                .domain(apiDTO.getStore().getDomain())
                .url(apiDTO.getUrl())
                .build();
    }

    /**
     * Convierte lista de StoreInfoApiDTO a lista de StoreDTO
     */
    public List<StoreDTO> toStoreDTOList(List<StoreInfoApiDTO> apiDTOs) {
        if (apiDTOs == null) {
            return Collections.emptyList();
        }

        return apiDTOs.stream()
                .map(this::toStoreDTO)
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Convierte StoreListApiResponseDTO a lista de StoreDTO
     */
    public List<StoreDTO> toStoreDTOListFromResponse(StoreListApiResponseDTO apiResponse) {
        if (apiResponse == null || apiResponse.getResults() == null) {
            return Collections.emptyList();
        }

        return apiResponse.getResults().stream()
                .map(store -> StoreDTO.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .slug(store.getSlug())
                        .domain(store.getDomain())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Extrae solo los nombres de las tiendas
     */
    public List<String> extractStoreNames(List<StoreInfoApiDTO> stores) {
        if (stores == null) {
            return Collections.emptyList();
        }

        return stores.stream()
                .filter(s -> s.getStore() != null)
                .map(s -> s.getStore().getName())
                .collect(Collectors.toList());
    }
}