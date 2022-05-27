package com.gameofthrones.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.model.Character;
import com.gameofthrones.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.cloud.config.enabled=false")
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindCharacters() throws Exception {
        List<Character> characters = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotCharacters.json").toFile(), new TypeReference<List<Character>>() {});
        when(characterService.findCharacters()).thenReturn(characters);

        //when
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/characters")
                                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        List<Character> resultCharacters = objectMapper.readValue(content, new TypeReference<List<Character>>() {});

        assertThat((Objects.requireNonNull(resultCharacters)).size()).isEqualTo(4);

        assertThat(resultCharacters.get(0).getName())
                .isEqualTo(characters.get(0).getName());
    }


}
