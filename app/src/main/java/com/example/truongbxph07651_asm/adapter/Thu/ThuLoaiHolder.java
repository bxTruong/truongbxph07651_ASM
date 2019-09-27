package com.example.truongbxph07651_asm.adapter.Thu;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;

class ThuLoaiHolder extends RecyclerView.ViewHolder {
    public TextView tvNameThu,tvIDThu;
    public ImageButton btnSuaTHu, btnXoaThu;
    public ThuLoaiHolder(@NonNull View itemView) {
        super(itemView);

        tvIDThu=itemView.findViewById(R.id.tvIDThu);
        tvNameThu=itemView.findViewById(R.id.tvNameThu);
        btnSuaTHu=itemView.findViewById(R.id.btnSuaThu);
        btnXoaThu=itemView.findViewById(R.id.btnXoaThu);
    }
}
