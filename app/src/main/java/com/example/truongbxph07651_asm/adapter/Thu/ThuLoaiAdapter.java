package com.example.truongbxph07651_asm.adapter.Thu;

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

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;

import java.util.List;

public class ThuLoaiAdapter extends RecyclerView.Adapter<ThuLoaiHolder> {
    private Context context;
    private List<ThuLoaiModel> thuModelList;
    private AlertDialog alertDialog;
    private ThuLoaiDao thuLoaiDao;


    public ThuLoaiAdapter(Context context, List<ThuLoaiModel> thuModelList) {
        this.context = context;
        this.thuModelList = thuModelList;
    }

    @NonNull
    @Override
    public ThuLoaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thu_loai, parent, false);
        ThuLoaiHolder thuHolder = new ThuLoaiHolder(view);
        return thuHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuLoaiHolder holder, final int position) {
        final ThuLoaiModel thuLoaiModel = thuModelList.get(position);
        holder.tvNameThu.setText(thuLoaiModel.getName());
        holder.tvIDThu.setText(String.valueOf(position+1));

        thuLoaiDao=new ThuLoaiDao(context);

        //Sửa ở List
        holder.btnSuaTHu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View dialog=LayoutInflater.from(context).inflate(R.layout.loai_dialog,null);
                builder.setTitle("Sửa Loại Thu");
                builder.setView(dialog);

                final EditText edtThemThu=dialog.findViewById(R.id.edtThuThemLoai);
                Button btnThemThu=dialog.findViewById(R.id.btnThemThuLoai);
                Button btnHuyThu=dialog.findViewById(R.id.btnHuyThuLoai);

                btnThemThu.setText("Sửa");

                edtThemThu.setText(thuLoaiModel.getName());

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

                        thuLoaiModel.setName(edtThemThu.getText().toString());

                        if ( edtThemThu.getText().toString().equals("")) {
                            Toast.makeText(context, "Mời Bạn Nhập Tên", Toast.LENGTH_SHORT).show();
                        } else {
                            int update = thuLoaiDao.UpdateThu(thuLoaiModel);
                            if (update > 0) {
                                thuModelList.set(position,thuLoaiModel);
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

        //Xóa Ở List
        holder.btnXoaThu.setOnClickListener(new View.OnClickListener() {
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
                        int delete = thuLoaiDao.DeleteThu(thuLoaiModel.getId());
                        if (delete > 0) {
                            thuModelList.remove(thuLoaiModel);
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
    }

    @Override
    public int getItemCount() {
        return thuModelList.size();
    }
}
