package com.gameofthrones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterDto {

    String url;
    String name;
    String gender;
    String culture;
    String born;
    String died;
    List<String> titles;
    List<String> aliases;
    String father;
    String mother;
    String spouse;
    List<String> allegiances;
    List<String> books;
    List<String> povBooks;
    List<String> tvSeries;
    List<String> playedBy;
}
