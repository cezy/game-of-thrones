package com.gameofthrones.client;

import com.gameofthrones.model.House;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface HouseClient {

    @RequestLine("GET /houses/{id}")
    @Headers("Content-Type: application/json")
    House getHouse(@Param int id);


    @RequestLine("GET /houses")
    @Headers("Content-Type: application/json")
    List<House> getHouses();


}
