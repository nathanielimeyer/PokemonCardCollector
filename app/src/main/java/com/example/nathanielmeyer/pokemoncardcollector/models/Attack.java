package com.example.nathanielmeyer.pokemoncardcollector.models;

import java.util.ArrayList;

public class Attack {
    private ArrayList<String> mCost  = new ArrayList<>();
    private String mName;
    private String mText;
    private int mDamage;
    private int mConvertedEnergyCost;

    public Attack(ArrayList<String> cost, String name, String text, int damage, int convertedEnergyCost) {
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

    public int getDamage() {
        return mDamage;
    }

    public int getConvertedEnergyCost() {
        return mConvertedEnergyCost;
    }
}
