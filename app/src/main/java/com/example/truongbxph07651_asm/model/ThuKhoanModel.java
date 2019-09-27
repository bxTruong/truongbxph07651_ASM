package com.example.truongbxph07651_asm.model;

public class ThuKhoanModel {
    private String name, date,loaithu;
    private double price;
    private  int id;


    public ThuKhoanModel(String name, String date, String loaithu, double price) {
        this.name = name;
        this.date = date;
        this.loaithu = loaithu;
        this.price = price;
    }

    public ThuKhoanModel() {
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

    public String getLoaithu() {
        return loaithu;
    }

    public void setLoaithu(String loaithu) {
        this.loaithu = loaithu;
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
