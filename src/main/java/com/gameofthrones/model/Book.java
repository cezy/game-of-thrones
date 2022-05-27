package com.gameofthrones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Book {

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
