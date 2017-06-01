package com.example.nathanielmeyer.pokemoncardcollector.models;

import java.util.ArrayList;

public class Card {
    private String mId;
    private String mName;
    private int mNationalPokedexNumber;
    private String mImageUrl;
    private String mImageUrlHiRes;
    private String mSubtype;
    private String mSupertype;
    private int mHp;
    private ArrayList<String> mRetreatCost = new ArrayList<>();
    private int mNumber;
    private String mArtist;
    private String mSeries;
    private String mSet;
    private String mSetCode;
    private ArrayList<String> mTypes = new ArrayList<>();
    private ArrayList<Attack> mAttacks = new ArrayList<>();
    private ArrayList<Weakness> mWeaknesses = new ArrayList<>();

    public Card(String id, String name, int nationalPokedexNumber, String imageUrl,
            String imageUrlHiRes, String subtype, String supertype, int hp,
            ArrayList<String> retreatCost, int number, String artist, String series, String set,
            String setCode, ArrayList<String> types, ArrayList<Attack> attacks,
            ArrayList<Weakness> weaknesses) {
        this.mId = id;
        this.mName = name;
        this.mNationalPokedexNumber = nationalPokedexNumber;
        this.mImageUrl = imageUrl;
        this.mImageUrlHiRes = imageUrlHiRes;
        this.mSubtype = subtype;
        this.mSupertype = supertype;
        this.mHp = hp;
        this.mRetreatCost = retreatCost;
        this.mNumber = number;
        this.mArtist = artist;
        this.mSeries = series;
        this.mSet = set;
        this.mSetCode = setCode;
        this.mTypes = types;
        this.mAttacks = attacks;
        this.mWeaknesses = weaknesses;
    }

    public String getId() { return mId; }
    public String getName() { return mName; }
    public int getNationalPokedexNumber() { return mNationalPokedexNumber; }
    public String getImageUrl() { return mImageUrl; }
    public String getmImageUrlHiRes() { return mImageUrlHiRes; }
    public String getSubtype() { return mSubtype; }
    public String getSupertype() { return mSupertype; }
    public int getHp() { return mHp; }
    public ArrayList<String> getRetreatCost() { return mRetreatCost; }
    public int getNumber() { return mNumber; }
    public String getArtist() { return mArtist; }
    public String getSeries() { return mSeries; }
    public String getSet() { return mSet; }
    public String getSetCode() { return mSetCode; }
    public ArrayList<String> getTypes() { return mTypes; }
    public ArrayList<Attack> getAttacks() { return mAttacks; }
    public ArrayList<Weakness> getWeaknesses() { return  mWeaknesses; }
}