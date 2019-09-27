package com.example.truongbxph07651_asm.fragment.ThongKe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.Chi.ChiKhoanAdapter;
import com.example.truongbxph07651_asm.adapter.ThongKe.ThongKeThuAdapter;
import com.example.truongbxph07651_asm.fragment.Chi.ChiKhoanFragment;
import com.example.truongbxph07651_asm.model.ChiKhoanModel;
import com.example.truongbxph07651_asm.model.ThongKeModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.sqlite.ChiKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;

import java.util.List;

public class ThongKeChiFragment extends Fragment {
    private List<ChiKhoanModel> chiKhoanModelList;
    private ThuKhoanModel thuKhoanModel;
    private ChiKhoanModel chiKhoanModel;
    private RecyclerView rcThongKeTHu;
    private List<ThongKeModel> thongKeModelList;
    private AlertDialog alertDialog;
    private ChiKhoanDao chiKhoanDao;
    private ThuKhoanDao thuKhoanDao;
    private ThongKeThuAdapter thongKeThuAdapter;
    private ThongKeModel thongKeModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thong_ke_chi_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcThongKeTHu = view.findViewById(R.id.rcThongKeChi);

        //Gọi ra lớp chứa database
        thuKhoanDao = new ThuKhoanDao(getContext());
        //List lấy hết dữ liệu từ database
        thongKeModelList = thuKhoanDao.tongChiNgay();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo
        thongKeThuAdapter = new ThongKeThuAdapter(getContext(), thongKeModelList);

        //Thay dổi adapter theo adapter tự tạo
        rcThongKeTHu.setAdapter(thongKeThuAdapter);

        LinearLayoutManager vertical = new LinearLayoutManager(getContext());
        rcThongKeTHu.setLayoutManager(vertical);

        thuKhoanDao = new ThuKhoanDao(getContext());
        double tongChi = thuKhoanDao.tongChi();
        TextView tvTongThu = view.findViewById(R.id.tvTongChi);

        chiKhoanDao = new ChiKhoanDao(getContext());
        chiKhoanModelList = chiKhoanDao.getAllThu();

        for (int i = 0; i < chiKhoanModelList.size(); i++) {
            chiKhoanModel = chiKhoanModelList.get(i);
            if (chiKhoanModel.getPrice() != 0) {
                tvTongThu.setText(tongChi + "");
                break;
            } else {
                break;
            }
        }

        if(tvTongThu.getText().toString().equals("Tổng Thu")){
            tvTongThu.setText("Bạn Chưa Nhập Chi");
        }
    }
}
