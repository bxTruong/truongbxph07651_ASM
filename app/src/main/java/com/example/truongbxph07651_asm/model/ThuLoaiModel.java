package com.example.truongbxph07651_asm.model;

import java.util.Date;

public class ThuLoaiModel {
   private String name;
   private int id;

    public ThuLoaiModel() {
    }

    public ThuLoaiModel(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
