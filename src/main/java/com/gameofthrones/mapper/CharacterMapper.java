package com.gameofthrones.mapper;

import com.gameofthrones.dto.CharacterDto;
import com.gameofthrones.model.Character;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDto to(Character character);

    Character from(CharacterDto characterDto);

}