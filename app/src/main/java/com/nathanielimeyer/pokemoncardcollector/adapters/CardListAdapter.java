package com.nathanielimeyer.pokemoncardcollector.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.nathanielimeyer.pokemoncardcollector.ui.CardDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {
private ArrayList<Card> mCards = new ArrayList<>();
private Context mContext;

    public CardListAdapter(Context context, ArrayList<Card> cards) {
        mContext = context;
        mCards = cards;
        }

    @Override
    public CardListAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardListAdapter.CardViewHolder holder, int position) {
        holder.bindCard(mCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.cardImageView) ImageView mCardImageView;
        @Bind(R.id.cardNameTextView) TextView mNameTextView;
        @Bind(R.id.pokemonTypeView) TextView mPokemonTypeTextView;
        @Bind(R.id.hpTextView) TextView mHpTextView;

        private Context mContext;

        public CardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CardDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("cards", Parcels.wrap(mCards));
            mContext.startActivity(intent);
        }

        public void bindCard(Card card) {
            Picasso.with(mContext).load(card.getImageUrl()).into(mCardImageView);
            mNameTextView.setText(card.getName());
            mPokemonTypeTextView.setText(card.getTypes().get(0));
            mHpTextView.setText("HP: " + card.getHp());
        }
    }
}
