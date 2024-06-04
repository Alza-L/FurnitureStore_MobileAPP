package com.example.uas.HomeFragment;

import com.example.uas.R;

public class BarangData {

    private static Barang[] dataChair = {
            new Barang(1,"Premium Chair","Chair",50.20,R.drawable.chair_1,0),
            new Barang(2,"Chair for work","Chair",40.10,R.drawable.chair_2,0),
            new Barang(3,"Chair for study","Chair",30.50,R.drawable.chair_3,0)
    };

    private static Barang[] setFurnit = {
            new Barang(4,"Family comfort","Set",75.70,R.drawable.set_1,0),
            new Barang(5,"Set to chat","Set",60.50,R.drawable.set_2,0),
            new Barang(6,"With friends set","Set",50.20,R.drawable.set_3,0),
            new Barang(7,"Set for family","Set",40.10,R.drawable.set_4,0)
    };

    public static Barang[] getDataChair() {
        return dataChair;
    }

    public static Barang[] getSetFurnit() {
        return setFurnit;
    }

    public static Barang[] getAllItem() {
        int totalLength = dataChair.length + setFurnit.length;
        Barang[] allData = new Barang[totalLength];

        System.arraycopy(dataChair, 0, allData, 0, dataChair.length);

        System.arraycopy(setFurnit, 0, allData, dataChair.length, setFurnit.length);

        return allData;
    }

}
