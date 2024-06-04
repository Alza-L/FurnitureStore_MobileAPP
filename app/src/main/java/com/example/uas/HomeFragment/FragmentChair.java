package com.example.uas.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.uas.R;

public class FragmentChair extends Fragment {

    private GridView gridChair;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chair, container, false);

        Barang[] dataKategoriA = BarangData.getDataChair();

        GridAdapter gridAdapter = new GridAdapter(requireContext(), dataKategoriA);
        gridChair = view.findViewById(R.id.gridview_chair);
        gridChair.setAdapter(gridAdapter);

        gridChair.setSelector(android.R.color.transparent);
        return view;
    }
}