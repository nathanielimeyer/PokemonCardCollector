package com.nathanielimeyer.pokemoncardcollector.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.nathanielimeyer.pokemoncardcollector.ui.CardDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCardViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    public static final String TAG = FirebaseCardViewHolder.class.getSimpleName();
    public ImageView mCardImageView;

    View mView;
    Context mContext;

    public FirebaseCardViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindCard(Card card) {
        mCardImageView = (ImageView) mView.findViewById(R.id.cardImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.cardNameTextView);
        TextView pokemonTypeTextView = (TextView) mView.findViewById(R.id.pokemonTypeView);
        TextView hpTextView = (TextView) mView.findViewById(R.id.hpTextView);

        Picasso.with(mContext)
                .load(card.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mCardImageView);

        nameTextView.setText(card.getName());
        pokemonTypeTextView.setText(card.getTypes().get(0));
        hpTextView.setText("HP: " + card.getHp());
    }
}