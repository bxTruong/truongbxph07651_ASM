package com.example.truongbxph07651_asm.adapter.Chi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanSpinnerAdapter;
import com.example.truongbxph07651_asm.model.ChiKhoanModel;
import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ChiKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ChiLoaiDao;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;

import java.util.Calendar;
import java.util.List;

public class ChiKhoanAdapter extends RecyclerView.Adapter<ChiKhoanHolder> {
    private Context context;
    private AlertDialog alertDialog;
    private ChiKhoanDao chiKhoanDao;
    private Spinner spChiKhoan;
    private List<ChiKhoanModel> chiKhoanModelList;

    private ChiLoaiDao chiLoaiDao;
    private List<ChiLoaiModel> chiLoaiModelList;
    private ChiKhoanSpinnerAdapter chiKhoanSpinnerAdapter;

    public ChiKhoanAdapter(Context context, List<ChiKhoanModel> chiKhoanModelList) {
        this.context = context;
        this.chiKhoanModelList = chiKhoanModelList;
    }

    @NonNull
    @Override
    public ChiKhoanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thu_khoan, parent, false);
        ChiKhoanHolder thuKhoanHolder = new ChiKhoanHolder(view);
        return thuKhoanHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChiKhoanHolder holder, final int position) {
        final ChiKhoanModel chiKhoanModel = chiKhoanModelList.get(position);

        holder.tvNameThuKhoan.setText(chiKhoanModel.getName());
        holder.tvIdThuKhoan.setText(String.valueOf(position+1));
        holder.imgAnh.setImageResource(R.drawable.hoadonden);

        chiKhoanDao=new ChiKhoanDao(context);

        //Sửa ở List và database
        holder.btnSuaThuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View dialog=LayoutInflater.from(context).inflate(R.layout.khoan_dialog,null);
                builder.setTitle("Sửa Khoản Chi");
                builder.setView(dialog);

                final EditText edtNameThuKhoan=dialog.findViewById(R.id.edtNameKhoan);
                final EditText edtDateThuKhoan=dialog.findViewById(R.id.edtDateKhoan);
                final EditText edtPriceThukhoan=dialog.findViewById(R.id.edtPriceKhoan);
                spChiKhoan = dialog.findViewById(R.id.spKhoan);

                Button btnThemThu=dialog.findViewById(R.id.btnThemKhoan);
                Button btnHuyThu=dialog.findViewById(R.id.btnHuyKhoan);
                ImageButton btnDateThuKhoan=dialog.findViewById(R.id.btnDateKhoan);

                btnThemThu.setText("Sửa");

                //Spinner
                chiLoaiDao = new ChiLoaiDao(context);
                chiLoaiModelList = chiLoaiDao.getAllThu();
                ChiLoaiModel chiLoaiModelfake = new ChiLoaiModel();
                chiLoaiModelfake.setId(0);
                chiLoaiModelfake.setName("---Vui Lòng Chọn Loại Chi---");
                chiLoaiModelList.add(0,chiLoaiModelfake);
                chiKhoanSpinnerAdapter = new ChiKhoanSpinnerAdapter(chiLoaiModelList, context);
                spChiKhoan.setAdapter(chiKhoanSpinnerAdapter);

                //Show Dữ Liệu Đã Có Sẵn
                edtDateThuKhoan.setText(chiKhoanModel.getDate());
                edtNameThuKhoan.setText(chiKhoanModel.getName());
                edtPriceThukhoan.setText(String.valueOf(chiKhoanModel.getPrice()));
                Log.e("so luong",chiKhoanSpinnerAdapter.getCount()+"");
                for(int i=0; i<chiKhoanSpinnerAdapter.getCount();i++){
                    ChiLoaiModel chiLoaiModel = (ChiLoaiModel) spChiKhoan.getItemAtPosition(i);
                    String test=chiLoaiModel.getName();
                    Log.e("loai",test);
                    if(chiKhoanModel.getLoaichi().equals(test)){
                        Log.e("loai thu",chiKhoanModel.getLoaichi());
                        spChiKhoan.setSelection(i);
                        break;
                    }
                }

                btnDateThuKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar=Calendar.getInstance();
                        int year=calendar.get(Calendar.YEAR);
                        int month=calendar.get(Calendar.MONTH);
                        int day=calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String result=String.format(context.getString(R.string.date),year,month+1,day);
//                                Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                                edtDateThuKhoan.setText(result);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                //Nút Hủy Ở Sửa
                btnHuyThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtDateThuKhoan.setText("");
                        edtDateThuKhoan.setText("");
                        edtPriceThukhoan.setText("");

                        Toast.makeText(context,"Hủy Thành Công",Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                //Nút Sửa(Thêm)
                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String NameThuKhoan=edtNameThuKhoan.getText().toString();
                        String DateThuKhoan=edtDateThuKhoan.getText().toString();
                        String PriceThuKhoan=edtPriceThukhoan.getText().toString();
                        ChiLoaiModel chiLoaiModel = (ChiLoaiModel) spChiKhoan.getSelectedItem();
                        String LoaiThu=chiLoaiModel.getName();

                        if ( NameThuKhoan.equals("")||DateThuKhoan.equals("")||PriceThuKhoan.equals("")||LoaiThu.equals("---Vui Lòng Chọn Loại Thu---")) {
                            Toast.makeText(context, "Mời Bạn Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            try {
                                String NameThuKhoan1=edtNameThuKhoan.getText().toString();
                                String DateThuKhoan1=edtDateThuKhoan.getText().toString();
                                double PriceThuKhoan1=Double.parseDouble(edtPriceThukhoan.getText().toString());
                                String LoaiThu1=chiLoaiModel.getName();

                                chiKhoanModel.setName(NameThuKhoan1);
                                chiKhoanModel.setDate(DateThuKhoan1);
                                chiKhoanModel.setPrice(PriceThuKhoan1);
                                chiKhoanModel.setLoaichi(LoaiThu1);
                                //Đủ Spinner

                                int update = chiKhoanDao.UpdateThu(chiKhoanModel);
                                if (update > 0) {
                                    chiKhoanModelList.set(position,chiKhoanModel);
                                    Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                    alertDialog.cancel();
                                }
                                else {
                                    Toast.makeText(context, "Update Thất Bại", Toast.LENGTH_SHORT).show();
                                }

                            }
                            catch (Exception e){
                                Toast.makeText(context, "Nhập Tiền Phải Là Số", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.create();
                alertDialog=builder.show();
            }
        });

        //Xóa Ở List và database
        holder.btnXoaThuKhoan.setOnClickListener(new View.OnClickListener() {
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
                        int delete = chiKhoanDao.DeleteThu(chiKhoanModel.getId());
                        if (delete > 0) {
                            chiKhoanModelList.remove(chiKhoanModel);
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //Chi Tiết Khi Click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View dialog=LayoutInflater.from(context).inflate(R.layout.thu_khoan_chitiet,null);
                builder.setTitle("Thông Tin Chi tiết");
                builder.setView(dialog);

                TextView tvNameChiTiet=dialog.findViewById(R.id.tvNameChiTiet_Thu);
                TextView tvPriceChiTiet=dialog.findViewById(R.id.tvPriceChiTiet_Thu);
                TextView tvDateChiTiet=dialog.findViewById(R.id.tvDateChiTiet_Thu);
                TextView tvLoaichiTiet=dialog.findViewById(R.id.tvLoaiChiTiet_Thu);

//                thuKhoanDao=new ThuKhoanDao(context);
//                thuKhoanModelList=thuKhoanDao.getAllThu();

                tvNameChiTiet.setText(chiKhoanModel.getName());
                tvPriceChiTiet.setText(chiKhoanModel.getPrice()+"");
                tvDateChiTiet.setText(chiKhoanModel.getDate());
                tvLoaichiTiet.setText(chiKhoanModel.getLoaichi());

                builder.create();
                alertDialog=builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chiKhoanModelList.size();
    }

    public void setSpinnerAdapter() {

    }
}
