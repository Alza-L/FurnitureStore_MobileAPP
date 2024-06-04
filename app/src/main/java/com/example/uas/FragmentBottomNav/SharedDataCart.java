package com.example.uas.FragmentBottomNav;

import com.example.uas.HomeFragment.Barang;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SharedDataCart {

    private static ArrayList<Barang> dataCart = new ArrayList<>();
    private static ArrayList<String> dataOrder = new ArrayList<>();

    public static void setDataCart(ArrayList<Barang> sendDataCart) {
        dataCart = sendDataCart;
    }

    public static void setDataOrder(ArrayList<String> sendDataOrder) {
        dataOrder = sendDataOrder;
    }

    public static ArrayList<Barang> getDataCart() {
        return dataCart;
    }

    public static ArrayList<String> getDataOrder() { return dataOrder; }
}
