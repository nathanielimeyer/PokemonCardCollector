package com.nathanielimeyer.pokemoncardcollector.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.adapters.FirebaseCardViewHolder;
import com.nathanielimeyer.pokemoncardcollector.models.Card;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollectedCardsActivity extends AppCompatActivity {
    private DatabaseReference mCardReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    public static final String TAG = CollectedCardsActivity.class.getSimpleName();


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        mCardReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CARDS);
        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Card, FirebaseCardViewHolder>
                (Card.class, R.layout.card_list_item, FirebaseCardViewHolder.class,
                        mCardReference) {

            @Override
            protected void populateViewHolder(FirebaseCardViewHolder viewHolder,
                                              Card model, int position) {
                viewHolder.bindCard(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
