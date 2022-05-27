package com.gameofthrones.controller;

import com.gameofthrones.dto.CharacterDto;
import com.gameofthrones.mapper.CharacterMapper;
import com.gameofthrones.service.CharacterService;
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
@RequestMapping(value = "/api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    private final CharacterMapper characterMapper;

    @GetMapping
    public ResponseEntity<List<CharacterDto>> findBooks() {
        return ResponseEntity.ok(
                characterService.findCharacters()
                        .stream()
                        .map(characterMapper::to)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> findBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                characterMapper.to(characterService.findCharacter(id)));
    }

}
