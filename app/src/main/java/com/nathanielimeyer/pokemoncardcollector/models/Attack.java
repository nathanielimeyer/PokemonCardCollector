package com.nathanielimeyer.pokemoncardcollector.models;

import java.util.ArrayList;

public class Attack {
    private ArrayList<String> mCost  = new ArrayList<>();
    private String mName;
    private String mText;
    private String mDamage;
    private int mConvertedEnergyCost;

    public Attack(ArrayList<String> cost, String name, String text, String damage, int convertedEnergyCost) {
        this.mCost = cost;
        this.mName = name;
        this.mText = text;
        this.mDamage = damage;
        this.mConvertedEnergyCost = convertedEnergyCost;
    }

    public ArrayList<String> getCost() {
        return mCost;
    }

    public String getName() {
        return mName;
    }

    public String getText() {
        return mText;
    }

    public String getDamage() {
        return mDamage;
    }

    public int getConvertedEnergyCost() {
        return mConvertedEnergyCost;
    }
}
