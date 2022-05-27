package com.gameofthrones.controller;

import com.gameofthrones.dto.HouseDto;
import com.gameofthrones.mapper.HouseMapper;
import com.gameofthrones.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/houses")
public class HouseController {

    private final HouseService houseService;

    private final HouseMapper houseMapper;

    @GetMapping
    public ResponseEntity<List<HouseDto>> findHouses() {
        return ResponseEntity.ok(
                houseService.findHouses()
                        .stream()
                        .map(houseMapper::to)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseDto> findHouse(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                houseMapper.to(houseService.findHouse(id)));
    }
}
