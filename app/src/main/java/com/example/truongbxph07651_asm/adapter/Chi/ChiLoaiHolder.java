package com.example.truongbxph07651_asm.adapter.Chi;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;

class ChiLoaiHolder extends RecyclerView.ViewHolder {
    public TextView tvNameChi,tvIDChi;
    public ImageButton btnSuaChi, btnXoaChi;
    public ChiLoaiHolder(@NonNull View itemView) {
        super(itemView);

        tvNameChi=itemView.findViewById(R.id.tvNameChiLoai);
        tvIDChi=itemView.findViewById(R.id.tvIDChiLoai);
        btnSuaChi=itemView.findViewById(R.id.btnSuaChi);
        btnXoaChi=itemView.findViewById(R.id.btnXoaChi);
    }
}
