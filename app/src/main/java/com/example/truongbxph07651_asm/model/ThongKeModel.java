package com.example.truongbxph07651_asm.model;

public class ThongKeModel {
    private double tong;
    private String date;

    public ThongKeModel(double tong, String date) {
        this.tong = tong;
        this.date = date;
    }

    public ThongKeModel() {
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
