package com.example.truongbxph07651_asm.adapter.Thu;

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

import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;

import java.util.Calendar;
import java.util.List;

public class ThuKhoanAdapter extends RecyclerView.Adapter<ThuKhoanHolder> {
    private Context context;
    private List<ThuKhoanModel> thuKhoanModelList;
    private AlertDialog alertDialog;
    private ThuKhoanDao thuKhoanDao;
    private Spinner spThuKhoan;

    private ThuLoaiDao thuLoaiDao;
    private List<ThuLoaiModel> thuLoaiModelList;
    private ThuKhoanSpinnerAdapter thuKhoanSpinnerAdapter;


    public ThuKhoanAdapter(Context context, List<ThuKhoanModel> thuKhoanModelList) {
        this.context = context;
        this.thuKhoanModelList = thuKhoanModelList;
    }

    @NonNull
    @Override
    public ThuKhoanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thu_khoan, parent, false);
        ThuKhoanHolder thuKhoanHolder = new ThuKhoanHolder(view);
        return thuKhoanHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThuKhoanHolder holder, final int position) {
        final ThuKhoanModel thuKhoanModel = thuKhoanModelList.get(position);

        holder.tvNameThuKhoan.setText(thuKhoanModel.getName());
        holder.tvIdThuKhoan.setText(String.valueOf(position + 1));
//        holder.tvDateThuKhoan.setText(thuKhoanModel.getDate());
//        holder.tvPriceThuKhoan.setText(String.valueOf(thuKhoanModel.getPrice()));
//        holder.tvLoaiThuKhoan.setText(thuKhoanModel.getLoaithu());
//
//        holder.tvLoaiThuKhoan.setVisibility(View.GONE);
//        holder.tvPriceThuKhoan.setVisibility(View.GONE);
//        holder.tvDateThuKhoan.setVisibility(View.GONE);

        thuKhoanDao = new ThuKhoanDao(context);

        //Sửa ở List và database
        holder.btnSuaThuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialog = LayoutInflater.from(context).inflate(R.layout.khoan_dialog, null);
                builder.setTitle("Sửa Khoản Thu");
                builder.setView(dialog);

                final EditText edtNameThuKhoan = dialog.findViewById(R.id.edtNameKhoan);
                final EditText edtDateThuKhoan = dialog.findViewById(R.id.edtDateKhoan);
                final EditText edtPriceThukhoan = dialog.findViewById(R.id.edtPriceKhoan);
                spThuKhoan = dialog.findViewById(R.id.spKhoan);

                Button btnThemThu = dialog.findViewById(R.id.btnThemKhoan);
                Button btnHuyThu = dialog.findViewById(R.id.btnHuyKhoan);
                ImageButton btnDateThuKhoan = dialog.findViewById(R.id.btnDateKhoan);

                btnThemThu.setText("Sửa");

                //Spinner
                thuLoaiDao = new ThuLoaiDao(context);
                thuLoaiModelList = thuLoaiDao.getAllThu();
                ThuLoaiModel thuLoaiModelfake = new ThuLoaiModel();
                thuLoaiModelfake.setId(0);
                thuLoaiModelfake.setName("---Vui Lòng Chọn Loại Thu---");
                thuLoaiModelList.add(0, thuLoaiModelfake);
                thuKhoanSpinnerAdapter = new ThuKhoanSpinnerAdapter(thuLoaiModelList, context);
                spThuKhoan.setAdapter(thuKhoanSpinnerAdapter);

                //Show Dữ Liệu Đã Có Sẵn
                edtDateThuKhoan.setText(thuKhoanModel.getDate());
                edtNameThuKhoan.setText(thuKhoanModel.getName());
                edtPriceThukhoan.setText(String.valueOf(thuKhoanModel.getPrice()));

//                String tenloaithu=thuKhoanModel.getLoaithu();
//                int stt=thuLoaiModelList.indexOf(tenloaithu);
//                spThuKhoan.setSelection(stt);


                Log.e("so luong",thuKhoanSpinnerAdapter.getCount()+"");
                for(int i=0; i<thuKhoanSpinnerAdapter.getCount();i++){
                    ThuLoaiModel thuLoaiModel1 = (ThuLoaiModel) spThuKhoan.getItemAtPosition(i);
                    String test=thuLoaiModel1.getName();
                    Log.e("loai",test);
                    if(thuKhoanModel.getLoaithu().equals(test)){
                        Log.e("loai thu",thuKhoanModel.getLoaithu());
                        spThuKhoan.setSelection(i);
                        break;
                    }
                }

                btnDateThuKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String result = String.format(context.getString(R.string.date), year, month + 1, day);
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
                        spThuKhoan.setSelection(0);
                        //Thiếu Spinner
                        Toast.makeText(context, "Hủy Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                //Nút Sửa(Thêm)
                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String NameThuKhoan = edtNameThuKhoan.getText().toString();
                        String DateThuKhoan = edtDateThuKhoan.getText().toString();
                        String PriceThuKhoan = edtPriceThukhoan.getText().toString();
                        ThuLoaiModel thuLoaiModel = (ThuLoaiModel) spThuKhoan.getSelectedItem();
                        String LoaiThu = thuLoaiModel.getName();

                        if (NameThuKhoan.equals("") || DateThuKhoan.equals("") || PriceThuKhoan.equals("") || LoaiThu.equals("---Vui Lòng Chọn Loại Thu---")) {
                            Toast.makeText(context, "Mời Bạn Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String NameThuKhoan1 = edtNameThuKhoan.getText().toString();
                                String DateThuKhoan1 = edtDateThuKhoan.getText().toString();
                                double PriceThuKhoan1 = Double.parseDouble(edtPriceThukhoan.getText().toString());
                                String LoaiThu1 = thuLoaiModel.getName();

                                thuKhoanModel.setName(NameThuKhoan1);
                                thuKhoanModel.setDate(DateThuKhoan1);
                                thuKhoanModel.setPrice(PriceThuKhoan1);
                                thuKhoanModel.setLoaithu(LoaiThu1);
                                //Đủ Spinner

                                int update = thuKhoanDao.UpdateThu(thuKhoanModel);
                                if (update > 0) {
                                    thuKhoanModelList.set(position, thuKhoanModel);
                                    Toast.makeText(context, "Update Thành Công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                    alertDialog.cancel();
                                } else {
                                    Toast.makeText(context, "Update Thất Bại", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(context, "Nhập Tiền Phải Là Số", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.create();
                alertDialog = builder.show();
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
                        int delete = thuKhoanDao.DeleteThu(thuKhoanModel.getId());
                        if (delete > 0) {
                            thuKhoanModelList.remove(thuKhoanModel);
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

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialog = LayoutInflater.from(context).inflate(R.layout.thu_khoan_chitiet, null);
                builder.setTitle("Thông Tin Chi tiết");
                builder.setView(dialog);

                TextView tvNameChiTiet = dialog.findViewById(R.id.tvNameChiTiet_Thu);
                TextView tvPriceChiTiet = dialog.findViewById(R.id.tvPriceChiTiet_Thu);
                TextView tvDateChiTiet = dialog.findViewById(R.id.tvDateChiTiet_Thu);
                TextView tvLoaichiTiet = dialog.findViewById(R.id.tvLoaiChiTiet_Thu);

//                thuKhoanDao=new ThuKhoanDao(context);
//                thuKhoanModelList=thuKhoanDao.getAllThu();

                tvNameChiTiet.setText(thuKhoanModel.getName());
                tvPriceChiTiet.setText(thuKhoanModel.getPrice() + "");
                tvDateChiTiet.setText(thuKhoanModel.getDate());
                tvLoaichiTiet.setText(thuKhoanModel.getLoaithu());
                Log.e("loai thu2",thuKhoanModel.getLoaithu());

                builder.create();
                alertDialog = builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return thuKhoanModelList.size();
    }



}
