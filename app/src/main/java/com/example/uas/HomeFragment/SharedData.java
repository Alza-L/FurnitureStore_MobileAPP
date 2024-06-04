package com.example.uas.HomeFragment;

import java.util.ArrayList;

public class SharedData {
    private static ArrayList<Barang> selectedBarangList = new ArrayList<>();
    private static boolean notif;

    public static void setSelectedBarang(Barang barang) {
        if (!selectedBarangList.contains(barang)) {
            selectedBarangList.add(barang);
            notif = true;
        } else {
            notif = false;
        }
    }

    public static boolean getNotifAdd() {
        return notif;
    }

    public static ArrayList<Barang> getSelectedBarangList() {
        return selectedBarangList;
    }
}