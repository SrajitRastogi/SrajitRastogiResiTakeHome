package com.Srajit.exercise.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.Srajit.exercise.models.NasaApiResponse;

@Component
public class NasaApi {
    
    @Value("${nasa.api.key}")
    private String apiKey;

    @Value("${nasa.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public NasaApiResponse getPhotosJson(String date) {
        URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("earth_date", date)
                .queryParam("api_key", apiKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, NasaApiResponse.class);
    }
}
