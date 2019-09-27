package com.example.truongbxph07651_asm.adapter.Chi;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;

import java.util.List;

public class ChiKhoanSpinnerAdapter implements SpinnerAdapter {
    private List<ChiLoaiModel> chiLoaiModelList;
    private Context context;

    public ChiKhoanSpinnerAdapter(List<ChiLoaiModel> chiLoaiModelList, Context context) {
        this.chiLoaiModelList = chiLoaiModelList;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.drop_row,parent,false);
        TextView tvSpinner1=convertView.findViewById(R.id.tvSpinner1);
        tvSpinner1.setText(chiLoaiModelList.get(position).getName());
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.selectedview_row,parent,false);
        TextView tvSpinner1=convertView.findViewById(R.id.tvSpinner1);
        tvSpinner1.setText(chiLoaiModelList.get(position).getName());
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    //Chuyển về số hàng spinner hiển thị
    @Override
    public int getCount() {
        return chiLoaiModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return chiLoaiModelList.get(position);
    }







    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
