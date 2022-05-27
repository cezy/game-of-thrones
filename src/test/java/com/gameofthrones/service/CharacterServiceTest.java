package com.gameofthrones.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.client.CharacterClient;
import com.gameofthrones.model.Character;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @InjectMocks
    private CharacterService characterService;

    @Mock
    private CharacterClient characterClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findCharacters() throws IOException {

        //given
        List<Character> characters = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotCharacters.json").toFile(), new TypeReference<List<Character>>() {
        });

        when(characterClient.getCharacters()).thenReturn(characters);

        //when
        List<Character> result = characterService.findCharacters();

        //then
        assertThat((Objects.requireNonNull(result)).size()).isEqualTo(4);

        assertThat(result.get(0).getName())
                .isEqualTo(characters.get(0).getName());
    }



}
