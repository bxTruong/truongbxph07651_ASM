package com.example.truongbxph07651_asm.adapter.Chi;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;

class ChiKhoanHolder extends RecyclerView.ViewHolder {
    public TextView tvNameThuKhoan, tvIdThuKhoan, tvPriceThuKhoan, tvDateThuKhoan, tvLoaiThuKhoan;
    public ImageButton btnSuaThuKhoan, btnXoaThuKhoan;
    public ImageView imgAnh;
    public ChiKhoanHolder(@NonNull View itemView) {
        super(itemView);

        tvIdThuKhoan=itemView.findViewById(R.id.tvIDThuKhoan);
        tvNameThuKhoan=itemView.findViewById(R.id.tvNameThuKhoan);
        tvDateThuKhoan=itemView.findViewById(R.id.tvDateThuKhoan);
        tvPriceThuKhoan=itemView.findViewById(R.id.tvPriceThuKhoan);
        tvLoaiThuKhoan=itemView.findViewById(R.id.tvLoaiThuKhoan);
        imgAnh=itemView.findViewById(R.id.imgAnh);

        btnSuaThuKhoan=itemView.findViewById(R.id.btnSuaThuKhoan);
        btnXoaThuKhoan=itemView.findViewById(R.id.btnXoaThuKhoan);
    }
}
