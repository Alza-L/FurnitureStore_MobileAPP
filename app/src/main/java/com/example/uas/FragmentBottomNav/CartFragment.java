package com.example.uas.FragmentBottomNav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.uas.HomeFragment.Barang;
import com.example.uas.HomeFragment.SharedData;
import com.example.uas.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private GridAdapterCart gridAdapterCart;
    private GridView gridCart;
    private ArrayList<Barang> selectedBarangList = SharedData.getSelectedBarangList();

    private ArrayList<Barang> dataBarang;
    private LinearLayout layoutNotEmpty;
    private CardView btnPayment;
    private TextView total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        layoutNotEmpty = view.findViewById(R.id.cart_not_empty);
        btnPayment = view.findViewById(R.id.btnPayment);
        total = view.findViewById(R.id.total);

        gridAdapterCart = new GridAdapterCart(requireContext(), selectedBarangList, total);

        if (!selectedBarangList.isEmpty()) {
            gridCart = view.findViewById(R.id.gridview_cart);
            gridCart.setAdapter(gridAdapterCart);
            gridCart.setSelector(android.R.color.transparent);

            dataBarang = gridAdapterCart.getData();
        }

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedDataCart.setDataCart(dataBarang);
                CardView clickced = (CardView) v;
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, .9f, 1f, .9f,
                        Animation.RESTART, 0.5f,
                        Animation.RESTART, 0.5f);
                scaleAnimation.setDuration(150);
                clickced.startAnimation(scaleAnimation);

                checkBeforePayment();
            }
        });

        return view;
    }

    private void checkBeforePayment() {
        double total = gridAdapterCart.getTotal();

        if (total != 0) {
            OrderFragment orderFragment = new OrderFragment();

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

            transaction.replace(R.id.frameLayout,orderFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        } else {
            Toast.makeText(requireContext(), "Make an order first!", Toast.LENGTH_SHORT).show();
        }
    }
}
