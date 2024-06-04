package com.example.uas.HomeFragment;

import android.content.Context;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.uas.HomeFragment.Barang;
import com.example.uas.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private Barang[] data;
    private  LayoutInflater inflater;

    public GridAdapter(Context context, Barang[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return data[position].getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView gridImage = convertView.findViewById(R.id.grid_image);
        TextView gridPrice = convertView.findViewById(R.id.grid_price);
        TextView gridName = convertView.findViewById(R.id.grid_text);
        CardView gridButton = convertView.findViewById(R.id.grid_button);

        gridImage.setImageResource(data[position].getImage());
        gridPrice.setText("$"+ String.valueOf(data[position].getPrice()));
        gridName.setText(data[position].getName());

        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView clickedGridButton = (CardView) v;

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, .9f, 1f, .9f,
                        Animation.RESTART, 0.5f,
                        Animation.RESTART, 0.5f);

                scaleAnimation.setDuration(150);

                clickedGridButton.startAnimation(scaleAnimation);

                Barang selectedBarang = data[position];
                String barangName = selectedBarang.getName();

                SharedData.setSelectedBarang(selectedBarang);

                if (SharedData.getNotifAdd()) {
                    Toast.makeText(context ,  barangName+" Add to Cart ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context ,  barangName+" Sudah ada ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
