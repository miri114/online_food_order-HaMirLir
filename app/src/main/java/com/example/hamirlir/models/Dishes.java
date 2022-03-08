package com.example.hamirlir.models;

public class Dishes {
    private int dish_id;
    private String dish_name;
    private String dish_imgUrl;
    private int foreign_keyKategori;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Dishes(int dish_id, String dish_name, String dish_imgUrl, int foreign_keyKategori) {
        this.dish_id = dish_id;
        this.dish_name = dish_name;
        this.dish_imgUrl = dish_imgUrl;
        this.foreign_keyKategori = foreign_keyKategori;
    }

    public Dishes(int price, String name, String imgUrl) {
        this.dish_name = name;
        this.dish_imgUrl = imgUrl;
        this.price = price;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_imgUrl() {
        return dish_imgUrl;
    }

    public void setDish_imgUrl(String dish_imgUrl) {
        this.dish_imgUrl = dish_imgUrl;
    }

    public int getForeign_keyKategori() {
        return foreign_keyKategori;
    }

    public void setForeign_keyKategori(int foreign_keyKategori) {
        this.foreign_keyKategori = foreign_keyKategori;
    }

}
