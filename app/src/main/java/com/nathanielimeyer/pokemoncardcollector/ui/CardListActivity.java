package com.nathanielimeyer.pokemoncardcollector.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.adapters.CardListAdapter;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.nathanielimeyer.pokemoncardcollector.services.PokemonTCGService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CardListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentSearch;
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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_QUERY_KEY, null);
        Log.d("Shared Pref Query", mRecentSearch);

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

                    }
                });
            }

        });
    }
}
