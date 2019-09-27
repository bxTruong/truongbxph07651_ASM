package com.example.truongbxph07651_asm.adapter.ThongKe;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;

class ThongKeThuHolder extends RecyclerView.ViewHolder {
    public TextView tvNameTK, tvIdTK , tvGiaTienTK;
    public ThongKeThuHolder(@NonNull View itemView) {
        super(itemView);
        tvNameTK=itemView.findViewById(R.id.tvNameTK);
        tvIdTK=itemView.findViewById(R.id.tvIDTK);
        tvGiaTienTK=itemView.findViewById(R.id.tvThongKeTien);
    }
}
