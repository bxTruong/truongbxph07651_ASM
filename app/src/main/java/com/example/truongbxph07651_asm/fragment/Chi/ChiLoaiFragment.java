package com.example.truongbxph07651_asm.fragment.Chi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.Chi.ChiLoaiAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuLoaiAdapter;
import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ChiLoaiDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ChiLoaiFragment extends Fragment {
    private View view;
    private RecyclerView rcChi;
    private FloatingActionButton fabChi;
    private List<ChiLoaiModel> chiLoaiModelList;
    private AlertDialog alertDialog;
    private ChiLoaiModel chiLoaiModel;
    private ChiLoaiAdapter chiLoaiAdapter;
    private ChiLoaiDao chiLoaiDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chi_loai_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabChi = view.findViewById(R.id.fabChi);
        rcChi = view.findViewById(R.id.rcChi);

        //Gọi ra lớp chứa database
        chiLoaiDao = new ChiLoaiDao(getContext());
        //List lấy hết dữ liệu từ database
        chiLoaiModelList = chiLoaiDao.getAllThu();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo
        editRecyclerVIew();

        fabChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialog = getLayoutInflater().from(getContext()).inflate(R.layout.loai_dialog, null);
                builder.setTitle("Thêm Loại Chi");
                builder.setView(dialog);
                final EditText edtThemThu = dialog.findViewById(R.id.edtThuThemLoai);
                Button btnThemThu = dialog.findViewById(R.id.btnThemThuLoai);
                Button btnHuyThu = dialog.findViewById(R.id.btnHuyThuLoai);

                btnHuyThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtThemThu.setText("");
                        Toast.makeText(getContext(), "Hủy Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtThemThu.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "Mời Bạn Nhập Tên", Toast.LENGTH_SHORT).show();
                        } else {
                            //Gọi ra đối tượng
                            chiLoaiModel = new ChiLoaiModel(edtThemThu.getText().toString());
                            chiLoaiDao.insertThu(chiLoaiModel);
                            chiLoaiModelList.add(chiLoaiModel);
                            editChiLoai();
                            Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.cancel();
                        }
                    }
                });
                builder.create();
                alertDialog = builder.show();
            }
        });
    }

    public void editChiLoai() {
        //Xóa toàn bộ danh sách trong list
        //Lúc này dữ liệu =0 chứ ko bằng null
        chiLoaiModelList.clear();
        //Sau đó thêm lại từ đầu
        chiLoaiModelList.addAll(chiLoaiDao.getAllThu());
        if (chiLoaiAdapter != null) {
            chiLoaiAdapter.notifyDataSetChanged();
            Log.e("thuadapter", "!=null");
        }
    }

    public void editRecyclerVIew() {
        if (chiLoaiAdapter == null) {
            chiLoaiAdapter = new ChiLoaiAdapter(getContext(), chiLoaiModelList);

            //Thay dổi adapter theo adapter tự tạo
            rcChi.setAdapter(chiLoaiAdapter);

            LinearLayoutManager vertical = new LinearLayoutManager(getContext());
            rcChi.setLayoutManager(vertical);

        } else {
            //Còn nếu đã khở tạo rồi thì không khưởi tạo nữa
            //notifyDataSetChanged nếu có dữ liệu thay đổi thì tự động cập nhật vào Adapter
            chiLoaiAdapter.notifyDataSetChanged();
        }
    }

}
