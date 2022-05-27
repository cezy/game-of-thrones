package com.gameofthrones.model;

import lombok.Data;

import java.util.List;

@Data
public class House {

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
