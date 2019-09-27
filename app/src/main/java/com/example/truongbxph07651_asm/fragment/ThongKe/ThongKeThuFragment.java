package com.example.truongbxph07651_asm.fragment.ThongKe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.ThongKe.ThongKeThuAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanSpinnerAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuLoaiAdapter;
import com.example.truongbxph07651_asm.model.ThongKeModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThongKeThuFragment extends Fragment {
    private List<ThuKhoanModel> thuKhoanModelList;
    private ThuKhoanModel thuKhoanModel;
    private RecyclerView rcThongKeTHu;
    private List<ThongKeModel> thongKeModelList;
    private AlertDialog alertDialog;
    private ThuKhoanDao thuKhoanDao;
    private ThongKeThuAdapter thongKeThuAdapter;
    private ThongKeModel thongKeModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thong_ke_thu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcThongKeTHu = view.findViewById(R.id.rcThongKeThu);

        //Gọi ra lớp chứa database
        thuKhoanDao = new ThuKhoanDao(getContext());
        //List lấy hết dữ liệu từ database
        thongKeModelList = thuKhoanDao.tongThuNgay();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo
        thongKeThuAdapter = new ThongKeThuAdapter(getContext(), thongKeModelList);

        //Thay dổi adapter theo adapter tự tạo
        rcThongKeTHu.setAdapter(thongKeThuAdapter);

        LinearLayoutManager vertical = new LinearLayoutManager(getContext());
        rcThongKeTHu.setLayoutManager(vertical);

        thuKhoanDao = new ThuKhoanDao(getContext());
        double tongThu = thuKhoanDao.tongThu();
        TextView tvTongThu = view.findViewById(R.id.tvTongThu);

        thuKhoanDao = new ThuKhoanDao(getContext());
        thuKhoanModelList = thuKhoanDao.getAllThu();

        for (int i = 0; i < thuKhoanModelList.size(); i++) {
            thuKhoanModel = thuKhoanModelList.get(i);
            if (thuKhoanModel.getPrice() != 0) {
                tvTongThu.setText(tongThu + "");
                break;
            } else {
                break;
            }
        }

        if(tvTongThu.getText().toString().equals("Tổng Thu")){
           tvTongThu.setText("Bạn Chưa Nhập Thu");
        }

    }
}
