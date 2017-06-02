package com.example.nathanielmeyer.pokemoncardcollector.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nathanielmeyer.pokemoncardcollector.R;
import com.example.nathanielmeyer.pokemoncardcollector.adapters.CardPagerAdapter;
import com.example.nathanielmeyer.pokemoncardcollector.models.Card;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private CardPagerAdapter adapterViewPager;
    ArrayList<Card> mCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        ButterKnife.bind(this);

        mCards = Parcels.unwrap(getIntent().getParcelableExtra("cards"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new CardPagerAdapter(getSupportFragmentManager(), mCards);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
