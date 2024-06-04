package com.example.uas.PaymentFragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uas.FragmentBottomNav.PaymentFragment;
import com.example.uas.R;

public class PaypalFragment extends Fragment {

    private CardView btn_confirm;
    private EditText input_transfer, input_cardNum, input_cvv, input_pin;
    private String transfer, cardNum, cvv, pin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paypal, container, false);

        input_transfer = view.findViewById(R.id.input_transfer);
        input_cardNum = view.findViewById(R.id.input_card_num);
        input_cvv = view.findViewById(R.id.input_cvv);
        input_pin = view.findViewById(R.id.input_pin);
        btn_confirm = view.findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer = input_transfer.getText().toString();
                cardNum = input_cardNum.getText().toString();
                cvv = input_cvv.getText().toString();
                pin = input_pin.getText().toString();

                if (!transfer.isEmpty() && !cardNum.isEmpty() && !cvv.isEmpty() && !pin.isEmpty()) {
                    SuccessFragment successFragment = new SuccessFragment();

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                    transaction.replace(R.id.frameLayout, successFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    Toast.makeText(requireContext(), "Complete the form to proceed to payment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}