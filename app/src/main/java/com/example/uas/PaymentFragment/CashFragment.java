package com.example.uas.PaymentFragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uas.FragmentBottomNav.SharedDataCart;
import com.example.uas.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class CashFragment extends Fragment {

    private ArrayList<String> dataOrder = SharedDataCart.getDataOrder();
    private CardView generate_qr;
    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash, container, false);

        generate_qr = view.findViewById(R.id.generate_qr);
        text = view.findViewById(R.id.text_qr);
        ImageView qr = view.findViewById(R.id.qr_code);

        int idRand = (int) (Math.random() * 101);
        String pickupInfo = "id:"+String.valueOf(idRand)+"|name:"+dataOrder.get(2)+"|email:"+dataOrder.get(3);

        generate_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate_qr.setCardBackgroundColor(android.graphics.Color.parseColor("#f5e7cb"));
                text.setTextColor(android.graphics.Color.parseColor("#4d4d4d"));
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(pickupInfo, BarcodeFormat.QR_CODE, 700, 700);

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    qr.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return view;
    }
}