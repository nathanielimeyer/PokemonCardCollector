package com.nathanielimeyer.pokemoncardcollector.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.nathanielimeyer.pokemoncardcollector.util.ItemTouchHelperAdapter;
import com.nathanielimeyer.pokemoncardcollector.util.OnStartDragListener;

public class FirebaseCardListAdapter extends FirebaseRecyclerAdapter<Card, FirebaseCardViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseCardListAdapter(Class<Card> modelClass, int modelLayout,
                                   Class<FirebaseCardViewHolder> viewHolderClass,
                                   Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseCardViewHolder viewHolder, Card model, int position) {
        viewHolder.bindCard(model);
        viewHolder.mCardImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}