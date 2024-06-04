package com.example.uas.HomeFragment;

public class Barang {

    private int id;
    private String name;
    private String category;
    private double price;
    private int image;
    private int count;



    public Barang(int id, String name, String category, double price, int image, int count) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getCategory() { return category; }

    public double getPrice() { return price; }

    public int getImage() { return image; }

    public int getCount() {
        return count;
    }

    public void minCount(int count) {
        this.count -= count;
    }

    public void plusCount(int count) {
        this.count += count;
    }
}
