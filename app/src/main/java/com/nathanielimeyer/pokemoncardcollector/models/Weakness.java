package com.nathanielimeyer.pokemoncardcollector.models;

public class Weakness {
    private String mType;
    private String mValue;

    public Weakness(String type, String value) {
        this.mType = type;
        this.mValue = value;
    }

    public String getType() {
        return mType;
    }

    public String getValue() {
        return mValue;
    }
}

