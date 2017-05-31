package com.example.nathanielmeyer.pokemoncardcollector.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nathanielmeyer.pokemoncardcollector.R;
import com.example.nathanielmeyer.pokemoncardcollector.services.PokemonTCGService;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResults extends AppCompatActivity {
    public static final String TAG = SearchResults.class.getSimpleName();

    @Bind(R.id.resultsTextView) TextView mResultsTextView;

    private String[] results = new String[] {"Pikachu", "Bulbasaur",
            "Flutterby", "Meowth", "Ekans", "Clefairy",
            "Clefable", "Charizard", "Squirtle", "Goldeen",
            "Lickitung", "Snorlax", "Dugduo", "Spearow", "Ratatat"};
    @Bind(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = ((TextView)view).getText().toString();
                Toast.makeText(SearchResults.this, result, Toast.LENGTH_LONG).show();

            }
        });

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
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
