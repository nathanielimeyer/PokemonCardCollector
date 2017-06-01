package com.example.nathanielmeyer.pokemoncardcollector.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.nathanielmeyer.pokemoncardcollector.models.Card;

import java.util.ArrayList;

/**
 * Created by nathanielmeyer on 6/1/17.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {
private ArrayList<Card> mCards = new ArrayList<>();
private Context mContext;

public CardListAdapter(Context context, ArrayList<Card> cards) {
        mContext = context;
        mCards = cards;
        }
        }