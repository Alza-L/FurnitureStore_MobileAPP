package com.example.uas.FragmentBottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.uas.HomeFragment.FragmentAll;
import com.example.uas.HomeFragment.FragmentChair;
import com.example.uas.R;

public class HomeFragment extends Fragment {

    private LinearLayout cardContainer;
    private CardView allCard, chairCard;
    private ImageView allIcon, chairIcon;
    private TextView allText, chairText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardContainer = view.findViewById(R.id.cardContainer);

        // Mendapatkan referensi ke CardView yang sudah ada
        allCard = view.findViewById(R.id.searchCard);
        allIcon = view.findViewById(R.id.allIcon);
        allText = view.findViewById(R.id.allText);
        chairCard = view.findViewById(R.id.chairCard);
        chairIcon = view.findViewById(R.id.chairIcon);
        chairText = view.findViewById(R.id.chairText);

        // Dedault Cart
        loadFragment(new FragmentAll());
        allCard.setCardBackgroundColor(getResources().getColor(R.color.card_clicked_background));
        allIcon.setImageResource(R.drawable.icon_all_clicked);
        allText.setTextColor(getResources().getColor(R.color.white));

        // Menambahkan OnClickListener pada CardView
        allCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardOnClick((CardView) view);
                loadFragment(new FragmentAll());
                updateCard(allCard, allText);
                allIcon.setImageResource(R.drawable.icon_all_clicked);
            }
        });

        chairCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardOnClick((CardView) view);
                loadFragment(new FragmentChair());
                updateCard(chairCard, chairText);
                chairIcon.setImageResource(R.drawable.icon_chair_clicked);
            }
        });

        return view;
    }

    private void cardOnClick(CardView cardSelected) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, .97f, 1f, .97f,
                Animation.RESTART, .5f,
                Animation.RESTART, .5f);
        scaleAnimation.setDuration(200);

        cardSelected.startAnimation(scaleAnimation);
    }

    private void loadFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateCard(CardView selectedCard, TextView selectedText) {
        resetCardBackground();

        selectedCard.setCardBackgroundColor(getResources().getColor(R.color.card_clicked_background));
        selectedText.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetCardBackground() {
        allCard.setCardBackgroundColor(getResources().getColor(R.color.card_default_background));
        allIcon.setImageResource(R.drawable.icon_all);
        allText.setTextColor(getResources().getColor(R.color.black));
        chairCard.setCardBackgroundColor(getResources().getColor(R.color.card_default_background));
        chairIcon.setImageResource(R.drawable.icon_chair);
        chairText.setTextColor(getResources().getColor(R.color.black));
    }
}
