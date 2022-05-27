package com.gameofthrones.service;

import com.gameofthrones.client.HouseClient;
import com.gameofthrones.model.House;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    @Value("${app.feign.config.url}")
    private String url;

    public List<House> findHouses() {
        HouseClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(HouseClient.class, url);
        return resource.getHouses();
    }

    public House findHouse(Integer id) {
        HouseClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(HouseClient.class, url);
        return resource.getHouse(id);
    }

}
