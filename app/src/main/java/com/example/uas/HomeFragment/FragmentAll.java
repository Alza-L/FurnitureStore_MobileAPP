package com.example.uas.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.uas.R;

public class FragmentAll extends Fragment {

    GridView gridAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        Barang[] dataKategoriA = BarangData.getAllItem();

        GridAdapter gridAdapter = new GridAdapter(requireContext(), dataKategoriA);
        gridAll = view.findViewById(R.id.gridview_all);
        gridAll.setAdapter(gridAdapter);

        gridAll.setSelector(android.R.color.transparent);
        return view;
    }
}