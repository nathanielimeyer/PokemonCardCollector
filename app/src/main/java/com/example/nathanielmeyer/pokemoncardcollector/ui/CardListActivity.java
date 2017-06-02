package com.example.nathanielmeyer.pokemoncardcollector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nathanielmeyer.pokemoncardcollector.R;
import com.example.nathanielmeyer.pokemoncardcollector.adapters.CardListAdapter;
import com.example.nathanielmeyer.pokemoncardcollector.models.Card;
import com.example.nathanielmeyer.pokemoncardcollector.services.PokemonTCGService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CardListActivity extends AppCompatActivity {
    public static final String TAG = CardListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private CardListAdapter mAdapter;

    @Bind(R.id.resultsTextView) TextView mResultsTextView;

    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Card> mCards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String searchString = intent.getStringExtra("searchString");

        mResultsTextView.setText("Results for " + searchString + ":");
        getCardsByName(searchString);
    }

    private void getCardsByName(String name) {
        final PokemonTCGService pokemonTCGService = new PokemonTCGService();
        pokemonTCGService.findCardsByName(name, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCards = PokemonTCGService.processResults(response);

                CardListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CardListAdapter(getApplicationContext(), mCards);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CardListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

//                        String[] cardNames = new String[mCards.size()];
//                        for (int i = 0; i < cardNames.length; i++) {
//                            cardNames[i] = mCards.get(i).getName();
//                        }
//
//                        ArrayAdapter adapter = new ArrayAdapter(CardListActivity.this, android.R.layout.simple_list_item_1, cardNames);
//                        mListView.setAdapter(adapter);

//                        for (Card card : mCards) {
//                            Log.d(TAG, "Id: " + card.getId());
//                            Log.d(TAG, "Name: " + card.getName());
//                            Log.d(TAG, "ImageUrl: " + card.getImageUrl());
//                            Log.d(TAG, "ImageUrlHiRes: " + card.getmImageUrlHiRes());
//                            Log.d(TAG, "Subtype: " + card.getSubtype());
//                            Log.d(TAG, "Supertype: " + card.getSupertype());
//                            Log.d(TAG, "Hp: " + card.getHp());
//                            Log.d(TAG, "Retreat Cost: " + card.getRetreatCost());
//                            Log.d(TAG, "Number: " + card.getNumber());
//                            Log.d(TAG, "Artist: " + card.getArtist());
//                            Log.d(TAG, "Series: " + card.getSeries());
//                            Log.d(TAG, "Set: " + card.getSet());
//                            Log.d(TAG, "SetCode: " + card.getSetCode());
//                            Log.d(TAG, "Types: " + card.getTypes());
//                            Log.d(TAG, "Attacks: " + card.getAttacks());
//                            Log.d(TAG, "Weaknesses: " + card.getWeaknesses());
//                        }
                    }
                });
            }

        });
    }
}
