package com.nathanielimeyer.pokemoncardcollector.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardDetailFragment extends Fragment {
    @Bind(R.id.cardImageView) ImageView mImageLabel;
    @Bind(R.id.cardNameTextView) TextView mNameLabel;
    @Bind(R.id.cardHpTextView) TextView mHpLabel;
    @Bind(R.id.cardTypeTextView) TextView mTypeLabel;
    @Bind(R.id.favoriteTextView) TextView mFavoriteLabel;
    @Bind(R.id.collectionTextView) TextView mCollectionLabel;
    @Bind(R.id.addToDeckTextView) TextView mAddToDeckLabel;
    @Bind(R.id.saveCardButton) TextView mSaveCardLabel;

    private Card mCard;

    public static CardDetailFragment newInstance(Card card) {
        CardDetailFragment cardDetailFragment = new CardDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("card", Parcels.wrap(card));
        cardDetailFragment.setArguments(args);
        return cardDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCard = Parcels.unwrap(getArguments().getParcelable("card"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mCard.getmImageUrlHiRes()).into(mImageLabel);
        mNameLabel.setText(mCard.getName());
        mHpLabel.setText("HP: " + mCard.getHp());
        if (mCard.getTypes().size() > 0) {
            mTypeLabel.setText(mCard.getTypes().get(0));
        }
        mFavoriteLabel.setText("Favorite coming soon");
        mCollectionLabel.setText("Collections coming soon");
        mAddToDeckLabel.setText("Decks coming soon");
        mSaveCardLabel.setText("Save coming soon");

        return view;
    }

}
