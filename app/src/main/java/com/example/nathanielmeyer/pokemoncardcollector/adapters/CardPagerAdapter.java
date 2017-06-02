package com.example.nathanielmeyer.pokemoncardcollector.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nathanielmeyer.pokemoncardcollector.models.Card;
import com.example.nathanielmeyer.pokemoncardcollector.ui.CardDetailFragment;

import java.util.ArrayList;

public class CardPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Card> mCards;

    public CardPagerAdapter(FragmentManager fm, ArrayList<Card> cards) {
        super(fm);
        mCards = cards;
    }

    @Override
    public Fragment getItem(int position) {
        return CardDetailFragment.newInstance(mCards.get(position));
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCards.get(position).getName();
    }
}
