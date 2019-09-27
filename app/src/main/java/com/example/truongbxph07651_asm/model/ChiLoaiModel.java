package com.example.truongbxph07651_asm.model;

public class ChiLoaiModel {
    private String name;
    private int id;

    public ChiLoaiModel(String name) {
        this.name = name;
    }

    public ChiLoaiModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
