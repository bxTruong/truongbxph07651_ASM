package com.example.truongbxph07651_asm.adapter.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.model.ThongKeModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;

import java.util.List;

public class ThongKeThuAdapter extends RecyclerView.Adapter<ThongKeThuHolder> {

    private Context context;
    private List<ThongKeModel> thongKeModelList;

    public ThongKeThuAdapter(Context context, List<ThongKeModel> thongKeModelList) {
        this.context = context;
        this.thongKeModelList = thongKeModelList;
    }

    @NonNull
    @Override
    public ThongKeThuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thong_ke, parent, false);
        ThongKeThuHolder thongKeThuHolder = new ThongKeThuHolder(view);
        return thongKeThuHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeThuHolder holder, int position) {
        final ThongKeModel thongKeModel = thongKeModelList.get(position);

        holder.tvNameTK.setText(thongKeModel.getDate());
        holder.tvIdTK.setText((position + 1)+".");
        holder.tvGiaTienTK.setText(thongKeModel.getTong()+"");
    }

    @Override
    public int getItemCount() {
        return thongKeModelList.size();
    }
}
