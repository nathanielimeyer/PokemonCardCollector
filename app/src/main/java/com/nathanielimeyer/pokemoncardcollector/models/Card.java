package com.nathanielimeyer.pokemoncardcollector.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Card {
    String mId;
    String mName;
    String mNationalPokedexNumber;
    String mImageUrl;
    String mImageUrlHiRes;
    String mSubtype;
    String mSupertype;
    int mHp;
    ArrayList<String> mRetreatCost = new ArrayList<>();
    String mNumber;
    String mArtist;
    String mSeries;
    String mSet;
    String mSetCode;
    String mRarity;
    String mText;
    ArrayList<String> mTypes = new ArrayList<>();

    public Card() {
    }

public Card(String id, String name, String nationalPokedexNumber, String imageUrl,
            String imageUrlHiRes, String subtype, String supertype, int hp,
            ArrayList<String> retreatCost, String number, String artist, String series, String set,
            String setCode, ArrayList<String> types, String rarity, String text) {
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
        this.mRarity = rarity;
        this.mText = text;
    }

    public String getId() { return mId; }
    public String getName() { return mName; }
    public String getNationalPokedexNumber() { return mNationalPokedexNumber; }
    public String getImageUrl() { return mImageUrl; }
    public String getmImageUrlHiRes() { return mImageUrlHiRes; }
    public String getSubtype() { return mSubtype; }
    public String getSupertype() { return mSupertype; }
    public int getHp() { return mHp; }
    public ArrayList<String> getRetreatCost() { return mRetreatCost; }
    public String getNumber() { return mNumber; }
    public String getArtist() { return mArtist; }
    public String getSeries() { return mSeries; }
    public String getSet() { return mSet; }
    public String getSetCode() { return mSetCode; }
    public ArrayList<String> getTypes() { return mTypes; }
    public String getRarity() { return mRarity; }
    public String getText() { return mText; }
}