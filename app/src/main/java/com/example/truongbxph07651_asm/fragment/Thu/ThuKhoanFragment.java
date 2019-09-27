package com.example.truongbxph07651_asm.fragment.Thu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanSpinnerAdapter;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class ThuKhoanFragment extends Fragment {

    private FloatingActionButton fabThuKhoan;
    private RecyclerView rcThuKhoan;
    private List<ThuKhoanModel> thuKhoanModelList;
    private AlertDialog alertDialog;
    private ThuKhoanDao thuKhoanDao;
    private ThuKhoanAdapter thuKhoanAdapter;
    private ThuKhoanModel thuKhoanModel;
    private ThuLoaiDao thuLoaiDao;
    private List<ThuLoaiModel> thuLoaiModelList;
    private ThuKhoanSpinnerAdapter thuKhoanSpinnerAdapter;
    private Spinner spThuKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thu_khoan_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcThuKhoan = view.findViewById(R.id.rcThuKhoan);
        fabThuKhoan = view.findViewById(R.id.fabThuKhoan);

        //Gọi ra lớp chứa database
        thuKhoanDao = new ThuKhoanDao(getContext());
        //List lấy hết dữ liệu từ database
        thuKhoanModelList = thuKhoanDao.getAllThu();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo

        if (thuKhoanAdapter == null) {
            thuKhoanAdapter = new ThuKhoanAdapter(getContext(), thuKhoanModelList);

            //Thay dổi adapter theo adapter tự tạo
            rcThuKhoan.setAdapter(thuKhoanAdapter);

            LinearLayoutManager vertical = new LinearLayoutManager(getContext());
            rcThuKhoan.setLayoutManager(vertical);

        } else {
            //Còn nếu đã khở tạo rồi thì không khưởi tạo nữa
            //notifyDataSetChanged nếu có dữ liệu thay đổi thì tự động cập nhật vào Adapter
            thuKhoanAdapter.notifyDataSetChanged();

        }

        fabThuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialog = getLayoutInflater().from(getContext()).inflate(R.layout.khoan_dialog, null);
                builder.setTitle("Thêm Khoản Thu");
                builder.setView(dialog);

                //Tìm các edittext
                final EditText edtNameThuKhoan = dialog.findViewById(R.id.edtNameKhoan);
                final EditText edtPriceThuKhoan = dialog.findViewById(R.id.edtPriceKhoan);
                final EditText edtDateThuKhoan = dialog.findViewById(R.id.edtDateKhoan);
                spThuKhoan = dialog.findViewById(R.id.spKhoan);
                //đủ spinner

                //Spinner
                thuLoaiDao = new ThuLoaiDao(getContext());
                thuLoaiModelList = thuLoaiDao.getAllThu();
                ThuLoaiModel thuLoaiModelfake = new ThuLoaiModel();
                thuLoaiModelfake.setId(0);
                thuLoaiModelfake.setName("---Vui Lòng Chọn Loại Thu---");
                thuLoaiModelList.add(0, thuLoaiModelfake);
                setSpinnerAdapter();


                //Tìm Các Nút
                Button btnThemThu = dialog.findViewById(R.id.btnThemKhoan);
                Button btnHuyThu = dialog.findViewById(R.id.btnHuyKhoan);
                ImageButton btnDateThuKhoan = dialog.findViewById(R.id.btnDateKhoan);

                //Date Picker
                btnDateThuKhoan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String result = String.format(getString(R.string.date), year, month + 1, day);
//                                Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
                                edtDateThuKhoan.setText(result);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                //Chức Năng Nút Hủy
                btnHuyThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(), "Hủy Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.cancel();
                    }
                });

                //Chức Năng Nút Thêm
                btnThemThu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String NameThuKhoan=edtNameThuKhoan.getText().toString();
                        String DateThuKhoan=edtDateThuKhoan.getText().toString();
                        String PriceThuKhoan=edtPriceThuKhoan.getText().toString();
                        ThuLoaiModel thuLoaiModel = (ThuLoaiModel) spThuKhoan.getSelectedItem();
                        String LoaiThu=thuLoaiModel.getName();

                        if ( NameThuKhoan.equals("")||DateThuKhoan.equals("")||PriceThuKhoan.equals("")||LoaiThu.equals("---Vui Lòng Chọn Loại Thu---")) {
                            Toast.makeText(getContext(), "Mời Bạn Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                double PriceThuKhoan1 = Double.parseDouble(edtPriceThuKhoan.getText().toString());
                                thuKhoanModel = new ThuKhoanModel(NameThuKhoan, DateThuKhoan, LoaiThu, PriceThuKhoan1);
                                thuKhoanDao.insertThu(thuKhoanModel);
                                editThuKhoan();
                                Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                                alertDialog.cancel();
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "Nhập Số Tiền Là Số", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.create();
                alertDialog = builder.show();
            }
        });

    }

    public void setSpinnerAdapter() {
//            if(thuKhoanSpinnerAdapter==null) {
                //Gọi hàm SinnerAdapter

                thuKhoanSpinnerAdapter = new ThuKhoanSpinnerAdapter(thuLoaiModelList, getContext());
                spThuKhoan.setAdapter(thuKhoanSpinnerAdapter);

//            spThuKhoan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    ThuLoaiModel thuLoaiModel = thuLoaiModelList.get(position);
//                    Toast.makeText(getContext(), thuLoaiModel.getName(), Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });
                Log.e("spinner", "day");
//            }

    }

    public void editThuKhoan() {
        //Xóa toàn bộ danh sách trong list
        //Lúc này dữ liệu =0 chứ ko bằng null
        thuKhoanModelList.clear();
        //Sau đó thêm lại từ đầu
        if (thuKhoanAdapter != null) {
            thuKhoanModelList.addAll(thuKhoanDao.getAllThu());
            thuKhoanAdapter.notifyDataSetChanged();
            Log.e("thukhoan", "!=null");
        }
    }
}
