package com.gameofthrones.mapper;

import com.gameofthrones.dto.HouseDto;
import com.gameofthrones.model.House;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    HouseDto to(House house);

    House from(HouseDto houseDto);

}
