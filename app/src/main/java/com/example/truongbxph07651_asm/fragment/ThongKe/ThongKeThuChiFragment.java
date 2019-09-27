package com.example.truongbxph07651_asm.fragment.ThongKe;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.model.ChiKhoanModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.sqlite.ChiKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;

import java.util.List;

public class ThongKeThuChiFragment extends Fragment {
    private ThuKhoanDao thuKhoanDao;
    private List<ThuKhoanModel> thuKhoanModelList;
    private ThuKhoanModel thuKhoanModel;
    private AlertDialog alertDialog;
    private ChiKhoanDao chiKhoanDao;
    private List<ChiKhoanModel> chiKhoanModelList;
    private ChiKhoanModel chiKhoanModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thong_ke_thu_chi_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thuKhoanDao = new ThuKhoanDao(getContext());
        thuKhoanModelList = thuKhoanDao.getAllThu();

        chiKhoanDao = new ChiKhoanDao(getContext());
        chiKhoanModelList = chiKhoanDao.getAllThu();


        TextView tvTongThu=view.findViewById(R.id.tvTongThu);
        TextView tvTongChi=view.findViewById(R.id.tvTongChi);
        TextView tvThongKe=view.findViewById(R.id.tvThongKe);
        TextView tvThongBao=view.findViewById(R.id.tvThongBao);
        ImageView imgThongBao=view.findViewById(R.id.imgThongBao);


        thuKhoanDao = new ThuKhoanDao(getContext());
        double tongThu = thuKhoanDao.tongThu();
        double tongChi = thuKhoanDao.tongChi();
        double thongke = thuKhoanDao.tongThu() - thuKhoanDao.tongChi();

        thuKhoanModelList=thuKhoanDao.getAllThu();
        chiKhoanModelList=chiKhoanDao.getAllThu();

        for(int i=0;i<thuKhoanModelList.size();i++) {
            thuKhoanModel=thuKhoanModelList.get(i);
            for(int j=0;i<chiKhoanModelList.size();j++) {
                chiKhoanModel = chiKhoanModelList.get(j);
                if (thuKhoanModel.getPrice() != 0) {
                    if (chiKhoanModel.getPrice() != 0) {
                        tvTongThu.setText(tongThu + "");
                        tvTongChi.setText(tongChi + "");
                        tvThongKe.setText(thongke + "");
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }

        if(tvThongKe.getText().toString().equals("Tổng Tiền")||tvTongThu.getText().toString().equals("Tổng Thu")||tvTongChi.getText().toString().equals("Tổng Chi")){
            imgThongBao.setImageResource(R.drawable.warning);
            tvThongBao.setText("Bạn Chưa Nhập Thu Chi");
        }
        else {
            if (thongke > 0) {
                imgThongBao.setImageResource(R.drawable.tienlai);
                tvThongBao.setText("Bạn Đã Có Tiền Lãi");
            } else if (thongke == 0) {
                imgThongBao.setImageResource(R.drawable.hoavon);
                tvThongBao.setText("Bạn Đã Hòa Vốn");
            } else if (thongke < 0) {
                imgThongBao.setImageResource(R.drawable.tienlo);
                tvThongBao.setText("Bạn Đã Lỗ Vốn");
            }
        }

    }
}
