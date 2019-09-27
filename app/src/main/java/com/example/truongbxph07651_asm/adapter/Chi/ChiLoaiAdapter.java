package com.example.truongbxph07651_asm.adapter.Chi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.sqlite.ChiLoaiDao;

import java.util.List;

public class ChiLoaiAdapter extends RecyclerView.Adapter<ChiLoaiHolder> {
    private Context context;
    private List<ChiLoaiModel> chiLoaiModelList;
    private AlertDialog alertDialog;
    private ChiLoaiDao chiLoaiDao;

    public ChiLoaiAdapter(Context context, List<ChiLoaiModel> chiLoaiModelList) {
        this.context = context;
        this.chiLoaiModelList = chiLoaiModelList;
    }

    @NonNull
    @Override
    public ChiLoaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chi, parent, false);
        ChiLoaiHolder chiLoaiHolder = new ChiLoaiHolder(view);
        return chiLoaiHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChiLoaiHolder holder, final int position) {
        final ChiLoaiModel chiLoaiModel = chiLoaiModelList.get(position);
        holder.tvNameChi.setText(chiLoaiModel.getName());
        holder.tvIDChi.setText(String.valueOf(position+1));

        chiLoaiDao=new ChiLoaiDao(context);

        holder.btnXoaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa Dữ Liệu Này");

                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Xóa Thát Bại", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int delete = chiLoaiDao.DeleteThu(chiLoaiModel.getId());
                        if (delete > 0) {
                            chiLoaiModelList.remove(chiLoaiModel);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //Sửa ở List
        holder.btnSuaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View dialog=LayoutInflater.from(context).inflate(R.layout.loai_dialog,null);
                builder.setTitle("Sửa Loại Chi");
                builder.setView(dialog);

                final EditText edtThemThu=dialog.findViewById(R.id.edtThuThemLoai);
                Button btnThemThu=dialog.findViewById(R.id.btnThemThuLoai);
                Button btnHuyThu=dialog.findViewById(R.id.btnHuyThuLoai);

                btnThemThu.setText("Sửa");

                edtThemThu.setText(chiLoaiModel.getName());

                btnHuyThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtThemThu.setText("");
                        Toast.makeText(context,"Hủy Thành Công",Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        chiLoaiModel.setName(edtThemThu.getText().toString());

                        if ( edtThemThu.getText().toString().equals("")) {
                            Toast.makeText(context, "Mời Bạn Nhập Tên", Toast.LENGTH_SHORT).show();
                        } else {
                            int update = chiLoaiDao.UpdateThu(chiLoaiModel);
                            if (update > 0) {
                                chiLoaiModelList.set(position,chiLoaiModel);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                                alertDialog.cancel();
                            }
                        }
                    }
                });
                builder.create();
                alertDialog=builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return chiLoaiModelList.size();
    }
}
