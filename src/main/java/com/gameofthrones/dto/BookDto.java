package com.gameofthrones.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    String url;
    String name;
    String isbn;
    List<String> authors;
    Integer numberOfPages;
    String publisher;
    String country;
    String mediaType;
    String released;
    List<String> characters;
    List<String> povCharacters;

}
