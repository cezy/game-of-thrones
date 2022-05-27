package com.gameofthrones.model;


import lombok.Data;

import java.util.List;

@Data
public class Character {

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
