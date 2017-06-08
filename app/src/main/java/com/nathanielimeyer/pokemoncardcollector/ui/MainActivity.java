package com.nathanielimeyer.pokemoncardcollector.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    
    private DatabaseReference mSearchedPokemonReference;

    @Bind(R.id.searchByNameButton) Button mSearchByNameButton;
    @Bind(R.id.searchText) EditText mSearchText;
    @Bind(R.id.textView) TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedPokemonReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_POKEMON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface amatic = Typeface.createFromAsset(getAssets(), "fonts/AmaticSC-Regular.ttf");
        mTextView.setTypeface(amatic);

        mSearchByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = mSearchText.getText().toString();
                if(!(searchString).equals("")){
                    addToSharedPreferences(searchString);
                    saveQueryToFirebase(searchString);
                }
                Toast.makeText(MainActivity.this, "Searching for " + searchString + "...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CardListActivity.class);
                intent.putExtra("searchString", searchString);
                startActivity(intent);
            }
        });
    }

    private void saveQueryToFirebase(String searchString) {
        mSearchedPokemonReference.push().setValue(searchString);
    }

    private void addToSharedPreferences(String searchString) {
        mEditor.putString(Constants.PREFERENCES_QUERY_KEY, searchString).apply();
    }
}
