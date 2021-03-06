package com.nathanielimeyer.pokemoncardcollector.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nathanielimeyer.pokemoncardcollector.Constants;
import com.nathanielimeyer.pokemoncardcollector.R;
import com.nathanielimeyer.pokemoncardcollector.models.Card;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.cardImageView) ImageView mImageLabel;
    @Bind(R.id.cardNameTextView) TextView mNameLabel;
    @Bind(R.id.cardHpTextView) TextView mHpLabel;
    @Bind(R.id.cardTypeTextView) TextView mTypeLabel;
//    @Bind(R.id.favoriteTextView) TextView mFavoriteLabel;
//    @Bind(R.id.collectionTextView) TextView mCollectionLabel;
    @Bind(R.id.addToDeckTextView) TextView mAddToDeckLabel;
    @Bind(R.id.saveCardButton) TextView mSaveCardButton;
    @Bind(R.id.goToBrowserTextView) TextView mBrowserLink;

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

        Picasso.with(view.getContext()).load(mCard.getImageUrlHiRes()).into(mImageLabel);
        mNameLabel.setText(mCard.getName());
        mHpLabel.setText("HP: " + mCard.getHp());
        if (!(mCard.getSupertype().equals("Pokémon"))) {
            mBrowserLink.setVisibility(View.GONE);
        }
        if (mCard.getTypes().size() > 0) {
            mTypeLabel.setText(mCard.getTypes().get(0));
        }
//        mFavoriteLabel.setText("Favorite coming soon");
//        mCollectionLabel.setText("Collections coming soon");
        mAddToDeckLabel.setText("Decks coming soon");

        mBrowserLink.setOnClickListener(this);
        mSaveCardButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveCardButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference cardRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CARDS)
                    .child(uid);
            DatabaseReference pushRef = cardRef.push();
            String pushId = pushRef.getKey();
            mCard.setPushId(pushId);
            pushRef.setValue(mCard);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mBrowserLink) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.pokemon.com/us/pokedex/" + mCard.getNationalPokedexNumber()));
            startActivity(webIntent);
        }
    }
}
