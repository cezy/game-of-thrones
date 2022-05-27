package com.gameofthrones.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.model.House;
import com.gameofthrones.service.HouseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
public class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testFindHouses() throws Exception {
        //given
        List<House> houses = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotHouses.json").toFile(), new TypeReference<List<House>>() {});

        when(houseService.findHouses()).thenReturn(houses);

        //when
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/houses")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        List<House> resultHouses = objectMapper.readValue(content, new TypeReference<List<House>>() {});

        assertThat((Objects.requireNonNull(resultHouses)).size()).isEqualTo(4);

        assertThat(resultHouses.get(0).getName())
                .isEqualTo(houses.get(0).getName());
    }

    @Test
    public void testFindHouse() throws Exception {
        House house = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotHouses.json").toFile(), new TypeReference<List<House>>() {}).get(0);
        when(houseService.findHouse(Mockito.any())).thenReturn(house);

        //when
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/houses/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        //then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
        String content = result.getResponse().getContentAsString();
        House resultHouse = objectMapper.readValue(content, new TypeReference<House>() {});

        Assertions.assertNotNull(resultHouse);

        assertThat(resultHouse.getName()).isEqualTo(house.getName());
    }

}
