package com.example.uas.FragmentBottomNav;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.FragmentBottomNav.SharedDataCart;
import com.example.uas.HomeFragment.Barang;
import com.example.uas.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    private ArrayList<Barang> dataCart = SharedDataCart.getDataCart();
    private ArrayList<String> dataOrder = new ArrayList<>();
    private CardView cart_pickUp, cart_delivery, btn_payment;
    private TextView pickUp_text, delivery_text, total_ammount;
    private TextView input_name, input_email, input_password;
    private String name, email, password;
    private  double total = total();
    private String orderMethode = "pickup";

    private double total() {
        double total = 0;
        for (Barang barang : dataCart) {
            total += (barang.getPrice() * barang.getCount());
        }
        return total;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        ImageView back = view.findViewById(R.id.back_to_cart);
        total_ammount = view.findViewById(R.id.total_ammount);
        total_ammount.setText("$"+String.valueOf(String.format("%.1f", total)));

        cart_pickUp = view.findViewById(R.id.cart_pickUp);
        cart_delivery = view.findViewById(R.id.cart_delivery);

        pickUp_text = view.findViewById(R.id.pickUp_text);
        delivery_text = view.findViewById(R.id.delivery_text);

        input_name = view.findViewById(R.id.input_name);
        input_email = view.findViewById(R.id.input_email);
        input_password = view.findViewById(R.id.input_password);

        btn_payment = view.findViewById(R.id.cart_payment);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                fragmentManager.popBackStack();
            }
        });

        cart_pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderMethode = "pickup";
                updateCart(cart_pickUp, pickUp_text);
            }
        });

        cart_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderMethode = "delivery";
                updateCart(cart_delivery, delivery_text);
            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = input_name.getText().toString();
                email = input_email.getText().toString();
                password = input_password.getText().toString();

                dataOrder.add(String.valueOf(total));
                dataOrder.add(orderMethode);
                dataOrder.add(name);
                dataOrder.add(email);
                dataOrder.add(password);

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    SharedDataCart.setDataOrder(dataOrder);

                    PaymentFragment paymentFragment = new PaymentFragment();

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                    transaction.replace(R.id.frameLayout, paymentFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    Toast.makeText(requireContext(), "Complete the form to proceed to payment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void updateCart(CardView cardView, TextView textView) {
        resetCart();

        cardView.setCardBackgroundColor(getResources().getColor(R.color.card_clicked_background));
        textView.setTextColor(getResources().getColor(R.color.white));
    }

    private void resetCart() {
        cart_pickUp.setCardBackgroundColor(getResources().getColor(R.color.card_default_background));
        cart_delivery.setCardBackgroundColor(getResources().getColor(R.color.card_default_background));
        pickUp_text.setTextColor(getResources().getColor(R.color.default_text));
        delivery_text.setTextColor(getResources().getColor(R.color.default_text));
    }
}