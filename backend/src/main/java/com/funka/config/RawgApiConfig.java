package com.funka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rawg.api")
public class RawgApiConfig {
    private String url;
    private String key;
    private Integer pageSize;
    private Integer timeout;

    public String buildUrl(String endpoint){
        return url + endpoint + "?key=" + key;
    }

    //* Construye URL con parámetros adicionales como String
    public String buildUrl(String endpoint, String additionalParams) {
        String baseUrl = buildUrl(endpoint);
        if (additionalParams != null && !additionalParams.isEmpty()) {
            return baseUrl + "&" + additionalParams;
        } else {
            return baseUrl;
        }
    }

    //* Construye URL con parámetros como pares clave-valor (varargs)
    public String buildUrl(String endpoint, String... params) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url + endpoint)
                .queryParam("key", key);

        // Agregar parámetros de 2 en 2
        for (int i = 0; i < params.length; i += 2) {
            String paramName = params[i];
            if (i + 1 < params.length && paramName != null && !paramName.isEmpty()) {
                String paramValue = params[i + 1];
                if (paramValue != null && !paramValue.isEmpty()) {
                    builder.queryParam(paramName, paramValue);
                }
            }
        }

        return builder.toUriString();
    }
}
