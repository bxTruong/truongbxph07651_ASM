package com.example.truongbxph07651_asm.fragment.Thu;

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
import com.example.truongbxph07651_asm.adapter.Thu.ThuLoaiAdapter;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThuLoaiFragment extends Fragment {

    private RecyclerView rcThu;
    private FloatingActionButton fabThu;
    private List<ThuLoaiModel> thuModelList;
    private AlertDialog alertDialog;
    private ThuLoaiDao thuLoaiDao;
    private ThuLoaiAdapter thuLoaiAdapter;
    private ThuLoaiModel thuLoaiModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.thu_loai_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fabThu = view.findViewById(R.id.fabThuLoai);
        rcThu = view.findViewById(R.id.rcThuLoai);

        //Gọi ra lớp chứa database
        thuLoaiDao=new ThuLoaiDao(getContext());
        //List lấy hết dữ liệu từ database
        thuModelList=thuLoaiDao.getAllThu();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo
        editRecyclerVIew();

        fabThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                View dialog=getLayoutInflater().from(getContext()).inflate(R.layout.loai_dialog,null);
                builder.setTitle("Thêm Loại Thu");
                builder.setView(dialog);

                final EditText edtThemThu=dialog.findViewById(R.id.edtThuThemLoai);

                Button btnThemThu=dialog.findViewById(R.id.btnThemThuLoai);
                Button btnHuyThu=dialog.findViewById(R.id.btnHuyThuLoai);


                btnHuyThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(),"Hủy Thành Công",Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edtThemThu.getText().toString().equals("")){
                            Toast.makeText(getContext(),"Mời Bạn Nhập Tên",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Gọi ra đối tượng
                            thuLoaiModel=new ThuLoaiModel(edtThemThu.getText().toString());
                            thuLoaiDao.insertThu(thuLoaiModel);
                            editThuLoai();
                            Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.cancel();
                        }
                    }
                });
                builder.create();
                alertDialog=builder.show();
            }
        });

    }
    public void editThuLoai() {
        //Xóa toàn bộ danh sách trong list
        //Lúc này dữ liệu =0 chứ ko bằng null
        thuModelList.clear();
        //Sau đó thêm lại từ đầu
        thuModelList.addAll(thuLoaiDao.getAllThu());
        if (thuLoaiAdapter != null) {
            thuLoaiAdapter.notifyDataSetChanged();
            Log.e("thuadapter","!=null");
        }
    }

    public void editRecyclerVIew(){
        if (thuLoaiAdapter == null) {
            thuLoaiAdapter = new ThuLoaiAdapter(getContext(), thuModelList);

            //Thay dổi adapter theo adapter tự tạo
            rcThu.setAdapter(thuLoaiAdapter);

            LinearLayoutManager vertical = new LinearLayoutManager(getContext());
            rcThu.setLayoutManager(vertical);

        } else {
            //Còn nếu đã khở tạo rồi thì không khưởi tạo nữa
            //notifyDataSetChanged nếu có dữ liệu thay đổi thì tự động cập nhật vào Adapter
            thuLoaiAdapter.notifyDataSetChanged();

        }
    }

    }

