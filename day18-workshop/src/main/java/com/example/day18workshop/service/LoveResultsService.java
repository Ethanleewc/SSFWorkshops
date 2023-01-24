package com.example.day18workshop.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.day18workshop.model.LoveResult;

@Service
public class LoveResultsService {

    private static final String LOVERESULT_ENTITY = "loveresultlist";

    private static final String LOVE_CALCULATOR_URL="https://love-calculator.p.rapidapi.com/getPercentage";

    public Optional<LoveResult> getLoveResult(String fname, String sname) throws IOException{
        String loveUrl = UriComponentsBuilder.fromUriString(LOVE_CALCULATOR_URL)
        .queryParam("sname", sname.replaceAll(" ", "+"))
        .queryParam("fname", fname.replaceAll(" ", "+"))
        .toUriString();
        System.out.println(loveUrl);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> rsep = testHeader(loveUrl, template);
        LoveResult loveResult = LoveResult.create(rsep.getBody());
        if(loveResult!=null)
        return Optional.of(loveResult);
        return Optional.empty();
    }

    public ResponseEntity<String> testHeader(String loveUrl, final RestTemplate restTemplate){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key","enter API key here");
        headers.set("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
        final HttpEntity<String> entity = new HttpEntity<String>(headers);

         // Execute the method writing your HttpEntity to the request
         ResponseEntity<String> response = restTemplate.exchange(loveUrl, HttpMethod.GET,
         entity, String.class);
         System.out.println(response);
        return response;

    }

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void save(final LoveResult lr){
        redisTemplate.opsForList()
            .leftPush(LOVERESULT_ENTITY, lr.getFname() + "And" + lr.getSname());
        redisTemplate.opsForHash()
            .put(LOVERESULT_ENTITY+ "_Map", lr.getFname() + "And" + lr.getSname(), lr);
    }

    public List<LoveResult> findAll(){
        List<Object> fromLoveResultList = redisTemplate.opsForList()
            .range(LOVERESULT_ENTITY, 0, redisTemplate.opsForList().size(LOVERESULT_ENTITY));
        List<LoveResult> lrs = redisTemplate.opsForHash()
            .multiGet(LOVERESULT_ENTITY+ "_Map", fromLoveResultList)
            .stream()
            .filter(LoveResult.class::isInstance)
            .map(LoveResult.class::cast)
            .toList();
        
        return lrs;
    }
    
}
