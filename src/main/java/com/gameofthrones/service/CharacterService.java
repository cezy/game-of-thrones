package com.gameofthrones.service;

import com.gameofthrones.client.CharacterClient;
import com.gameofthrones.model.Character;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Value("${app.feign.config.url}")
    private String url;

    public List<Character> findCharacters() {
        CharacterClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(CharacterClient.class, url);
        return resource.getCharacters();
    }

    public Character findCharacter(Integer id) {
        CharacterClient resource = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).target(CharacterClient.class, url);
        return resource.getCharacter(id);
    }
}
