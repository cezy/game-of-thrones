package com.gameofthrones.client;

import com.gameofthrones.config.CustomFeignClientConfiguration;
import com.gameofthrones.model.Character;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "character-feign-client", url = "${app.feign.config.url}",
        configuration = CustomFeignClientConfiguration.class)
public interface CharacterClient {

    @RequestLine("GET /characters/{id}")
    @Headers("Content-Type: application/json")
    Character getCharacter(@Param int id);


    @RequestLine("GET /characters")
    @Headers("Content-Type: application/json")
    List<Character> getCharacters();

}
