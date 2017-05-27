package com.example.nathanielmeyer.pokemoncardcollector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.searchByNameButton) Button mSearchByNameButton;
    @Bind(R.id.searchText) EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = mSearchText.getText().toString();
                Toast.makeText(MainActivity.this, "Searching for " + searchString + "...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SearchResults.class);
                intent.putExtra("searchString", searchString);
                startActivity(intent);
            }
        });
    }
}
