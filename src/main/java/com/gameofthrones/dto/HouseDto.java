package com.gameofthrones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDto {

    String url;
    String name;
    String region;
    String coatOfArms;
    String words;
    List<String> titles;
    List<String> seats;
    String currentLord;
    String heir;
    String overlord;
    String founded;
    String founder;
    String diedOut;
    List<String> ancestralWeapons;
    List<String> cadetBranches;
    List<String> swornMembers;

}
