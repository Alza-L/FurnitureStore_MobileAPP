package com.example.uas.FragmentBottomNav;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.uas.HomeFragment.Barang;
import com.example.uas.R;

import java.util.ArrayList;

public class GridAdapterCart extends BaseAdapter {

    private Context context;
    private ArrayList<Barang> data;
    private LayoutInflater inflater;
    private TextView totalPayment;

    public GridAdapterCart(Context context, ArrayList<Barang> data, TextView totalPayment) {
        this.context = context;
        this.data = data;
        this.totalPayment = totalPayment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_tem_cart, null);
        }

        ImageView imageProduk = convertView.findViewById(R.id.cart_image);
        ImageView btnMin = convertView.findViewById(R.id.cart_btn_min);
        ImageView btnPlus = convertView.findViewById(R.id.cart_btn_plus);
        TextView title = convertView.findViewById(R.id.cart_title);
        TextView price = convertView.findViewById(R.id.cart_price);
        TextView category = convertView.findViewById(R.id.cart_category);
        TextView counter = convertView.findViewById(R.id.cart_txt_count);
        CardView delete = convertView.findViewById(R.id.cart_delete);

        Barang barang = data.get(position);
        counter.setText(String.valueOf(barang.getCount()));
        updateTotal();

        title.setText(String.valueOf(barang.getName()));
        category.setText(String.valueOf(barang.getCategory()));
        price.setText("$" + String.valueOf(barang.getPrice()));
        imageProduk.setImageResource(barang.getImage());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView clickedGridButton = (CardView) v;
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, .9f, 1f, .9f,
                        Animation.RESTART, 0.5f,
                        Animation.RESTART, 0.5f);
                scaleAnimation.setDuration(150);
                clickedGridButton.startAnimation(scaleAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Delete " + barang.getName(), Toast.LENGTH_SHORT).show();
                        updateTotal();
                    }
                }, 300);
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barang.getCount() > 0) {
                    ImageView clickedGridButton = (ImageView) v;
                    animation(clickedGridButton);
                    barang.minCount(1);
                    counter.setText(String.valueOf(barang.getCount()));
                    updateTotal();
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView clickedGridButton = (ImageView) v;
                animation(clickedGridButton);
                barang.plusCount(1);
                counter.setText(String.valueOf(barang.getCount()));
                updateTotal();
            }
        });

        return convertView;
    }

    public void animation(ImageView clickedGridButton) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f, .9f, 1f, .9f,
                Animation.RESTART, 0.5f,
                Animation.RESTART, 0.5f);

        scaleAnimation.setDuration(150);

        clickedGridButton.startAnimation(scaleAnimation);
    }

    public void updateTotal() {
        double total = calculateTotal();
        String formattedTotal = formatTotal(total);
        if (totalPayment != null) {
            totalPayment.setText("$"+formattedTotal);
        }
    }

    private double calculateTotal() {
        double total = 0;
        for (Barang barang : data) {
            total += (barang.getPrice() * barang.getCount());
        }
        return total;
    }

    private String formatTotal(double total) {
        return String.format("%.1f", total);
    }

    public double getTotal() {
        return calculateTotal();
    }

    public ArrayList<Barang> getData() { return data; }
}
