package com.example.uas.FragmentBottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.uas.PaymentFragment.CashFragment;
import com.example.uas.PaymentFragment.PaypalFragment;
import com.example.uas.R;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {

    private ArrayList<String> dataOrder = SharedDataCart.getDataOrder();
    private ImageView back;
    private LinearLayout payment_cash, payment_paypal;
    private TextView cash_text, paypal_text, total_payment;
//    private String ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        loadFragment(new CashFragment());

        back = view.findViewById(R.id.back_to_order);

        payment_cash = view.findViewById(R.id.payment_cash);
        payment_paypal = view.findViewById(R.id.payment_paypal);
        cash_text = view.findViewById(R.id.cash_text);
        paypal_text = view.findViewById(R.id.paypal_text);

        total_payment = view.findViewById(R.id.total_payment);
        total_payment.setText("$"+String.valueOf(dataOrder.get(0)));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                fragmentManager.popBackStack();
            }
        });

        payment_cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CashFragment());
                resetStyle();
                payment_cash.setBackgroundColor(android.graphics.Color.parseColor("#f5e7cb"));
                cash_text.setTextColor(android.graphics.Color.parseColor("#4d4d4d"));
            }
        });

        payment_paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new PaypalFragment());
                resetStyle();
                payment_paypal.setBackgroundColor(android.graphics.Color.parseColor("#f5e7cb"));
                paypal_text.setTextColor(android.graphics.Color.parseColor("#4d4d4d"));
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frContainerPayment, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void resetStyle() {
        payment_cash.setBackgroundColor(getResources().getColor(R.color.card_default_background));
        cash_text.setTextColor(android.graphics.Color.parseColor("#969696"));
        payment_paypal.setBackgroundColor(getResources().getColor(R.color.card_default_background));
        paypal_text.setTextColor(android.graphics.Color.parseColor("#969696"));

    }
}