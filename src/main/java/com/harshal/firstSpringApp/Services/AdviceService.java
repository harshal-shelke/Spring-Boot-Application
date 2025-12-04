package com.harshal.firstSpringApp.Services;

import com.harshal.firstSpringApp.ApiResponse.AdviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdviceService {
    @Value("${weather.api.key}")
    private String apikey;
    private static final String url="https://api.api-ninjas.com/v1/advice";

    @Autowired
    private RestTemplate restTemplate;

    public AdviceResponse getAdvice(){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("X-Api-Key",apikey);
        HttpEntity<String> entity=new HttpEntity<>(httpHeaders);
        ResponseEntity<AdviceResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, AdviceResponse.class);
        return response.getBody();
    }
}
