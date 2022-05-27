package com.gameofthrones.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofthrones.client.HouseClient;
import com.gameofthrones.model.House;
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
public class HouseServiceTest {

    @InjectMocks
    private HouseService houseService;

    @Mock
    private HouseClient houseClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void findHouses() throws IOException {
        //given
        List<House> houses = objectMapper.readValue(Paths.get("src/test/resources/fixtures/gotHouses.json").toFile(), new TypeReference<List<House>>() {});
        when(houseClient.getHouses()).thenReturn(houses);

        //when
        List<House> result = houseService.findHouses();

        //then
        assertThat((Objects.requireNonNull(result)).size()).isEqualTo(4);


        assertThat(result.get(0).getName())
                .isEqualTo(houses.get(0).getName());
    }

}
