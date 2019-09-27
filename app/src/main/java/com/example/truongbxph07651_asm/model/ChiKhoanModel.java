package com.example.truongbxph07651_asm.model;

public class ChiKhoanModel {
    private String name, date,loaichi;
    private double price;
    private  int id;


    public ChiKhoanModel() {
    }

    public ChiKhoanModel(String name, String date, String loaichi, double price) {
        this.name = name;
        this.date = date;
        this.loaichi = loaichi;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoaichi() {
        return loaichi;
    }

    public void setLoaichi(String loaichi) {
        this.loaichi = loaichi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
