package com.nathanielimeyer.pokemoncardcollector.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Card {
    String id;
    String name;
    String nationalPokedexNumber;
    String imageUrl;
    String imageUrlHiRes;
    String subtype;
    String supertype;
    int hp;
    ArrayList<String> retreatCost = new ArrayList<>();
    String number;
    String artist;
    String series;
    String set;
    String setCode;
    String rarity;
    String text;
    ArrayList<String> types = new ArrayList<>();

    public Card() {
    }

public Card(String id, String name, String nationalPokedexNumber, String imageUrl,
            String imageUrlHiRes, String subtype, String supertype, int hp,
            ArrayList<String> retreatCost, String number, String artist, String series, String set,
            String setCode, ArrayList<String> types, String rarity, String text) {
        this.id = id;
        this.name = name;
        this.nationalPokedexNumber = nationalPokedexNumber;
        this.imageUrl = imageUrl;
        this.imageUrlHiRes = imageUrlHiRes;
        this.subtype = subtype;
        this.supertype = supertype;
        this.hp = hp;
        this.retreatCost = retreatCost;
        this.number = number;
        this.artist = artist;
        this.series = series;
        this.set = set;
        this.setCode = setCode;
        this.types = types;
        this.rarity = rarity;
        this.text = text;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getNationalPokedexNumber() { return nationalPokedexNumber; }
    public String getImageUrl() { return imageUrl; }
    public String getmImageUrlHiRes() { return imageUrlHiRes; }
    public String getSubtype() { return subtype; }
    public String getSupertype() { return supertype; }
    public int getHp() { return hp; }
    public ArrayList<String> getRetreatCost() { return retreatCost; }
    public String getNumber() { return number; }
    public String getArtist() { return artist; }
    public String getSeries() { return series; }
    public String getSet() { return set; }
    public String getSetCode() { return setCode; }
    public ArrayList<String> getTypes() { return types; }
    public String getRarity() { return rarity; }
    public String getText() { return text; }
}