package com.example.nathanielmeyer.pokemoncardcollector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResults extends AppCompatActivity {
    @Bind(R.id.resultsTextView) TextView mResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String searchString = intent.getStringExtra("searchString");
        mResultsTextView.setText("Results for " + searchString + ":");
    }
}
