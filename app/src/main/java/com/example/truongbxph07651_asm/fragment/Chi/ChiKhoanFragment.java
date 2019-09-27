package com.example.truongbxph07651_asm.fragment.Chi;

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
import com.example.truongbxph07651_asm.adapter.Chi.ChiKhoanAdapter;
import com.example.truongbxph07651_asm.adapter.Chi.ChiKhoanSpinnerAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanAdapter;
import com.example.truongbxph07651_asm.adapter.Thu.ThuKhoanSpinnerAdapter;
import com.example.truongbxph07651_asm.model.ChiKhoanModel;
import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;
import com.example.truongbxph07651_asm.sqlite.ChiKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ChiLoaiDao;
import com.example.truongbxph07651_asm.sqlite.ThuKhoanDao;
import com.example.truongbxph07651_asm.sqlite.ThuLoaiDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

public class ChiKhoanFragment extends Fragment {
    private FloatingActionButton fabThuKhoan;
    private RecyclerView rcThuKhoan;
    private List<ChiKhoanModel> chiKhoanModelList;
    private AlertDialog alertDialog;
    private ChiKhoanDao chiKhoanDao;
    private ChiKhoanAdapter chiKhoanAdapter;
    private ChiKhoanModel chiKhoanModel;
    private ChiLoaiDao chiLoaiDao;
    private List<ChiLoaiModel> chiLoaiModelList;
    private ChiKhoanSpinnerAdapter chiKhoanSpinnerAdapter;
    private Spinner spChiKhoan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chi_khoan_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcThuKhoan = view.findViewById(R.id.rcThuKhoan);
        fabThuKhoan = view.findViewById(R.id.fabThuKhoan);

        //Gọi ra lớp chứa database
        chiKhoanDao = new ChiKhoanDao(getContext());
        //List lấy hết dữ liệu từ database
        chiKhoanModelList = chiKhoanDao.getAllThu();
        //Nếu Adapter chưa dược khởi tạo thì khởi tạo

        if (chiKhoanAdapter == null) {
            chiKhoanAdapter = new ChiKhoanAdapter(getContext(), chiKhoanModelList);

            //Thay dổi adapter theo adapter tự tạo
            rcThuKhoan.setAdapter(chiKhoanAdapter);

            LinearLayoutManager vertical = new LinearLayoutManager(getContext());
            rcThuKhoan.setLayoutManager(vertical);

        } else {
            //Còn nếu đã khở tạo rồi thì không khưởi tạo nữa
            //notifyDataSetChanged nếu có dữ liệu thay đổi thì tự động cập nhật vào Adapter
            chiKhoanAdapter.notifyDataSetChanged();

        }

        fabThuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tạo Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialog = getLayoutInflater().from(getContext()).inflate(R.layout.khoan_dialog, null);
                builder.setTitle("Thêm Khoản Chi");
                builder.setView(dialog);

                //Tìm các edittext
                final EditText edtNameThuKhoan = dialog.findViewById(R.id.edtNameKhoan);
                final EditText edtPriceThuKhoan = dialog.findViewById(R.id.edtPriceKhoan);
                final EditText edtDateThuKhoan = dialog.findViewById(R.id.edtDateKhoan);
                spChiKhoan = dialog.findViewById(R.id.spKhoan);
                //đủ spinner

                //Spinner
                chiLoaiDao = new ChiLoaiDao(getContext());
                chiLoaiModelList = chiLoaiDao.getAllThu();
                ChiLoaiModel chiLoaiModelfake = new ChiLoaiModel();
                chiLoaiModelfake.setId(0);
                chiLoaiModelfake.setName("---Vui Lòng Chọn Loại Chi---");
                chiLoaiModelList.add(0, chiLoaiModelfake);
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
                        edtDateThuKhoan.setText("");
                        edtDateThuKhoan.setText("");
                        edtPriceThuKhoan.setText("");

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
                        ChiLoaiModel chiLoaiModel = (ChiLoaiModel) spChiKhoan.getSelectedItem();
                        String LoaiThu=chiLoaiModel.getName();

                        if ( NameThuKhoan.equals("")||DateThuKhoan.equals("")||PriceThuKhoan.equals("")||LoaiThu.equals("---Vui Lòng Chọn Loại Thu---")) {
                            Toast.makeText(getContext(), "Mời Bạn Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                String NameThuKhoan1 = edtNameThuKhoan.getText().toString();
                                String DateThuKhoan1 = edtDateThuKhoan.getText().toString();
                                double PriceThuKhoan1 = Double.parseDouble(edtPriceThuKhoan.getText().toString());
                                String LoaiThu1=chiLoaiModel.getName();
                                chiKhoanModel = new ChiKhoanModel(NameThuKhoan1, DateThuKhoan1, LoaiThu1, PriceThuKhoan1);
                                chiKhoanDao.insertThu(chiKhoanModel);
                                chiKhoanModelList.add(chiKhoanModel);
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
        chiKhoanSpinnerAdapter = new ChiKhoanSpinnerAdapter(chiLoaiModelList, getContext());
        spChiKhoan.setAdapter(chiKhoanSpinnerAdapter);
        Log.e("spinner", "day");
    }

    public void editThuKhoan() {
        //Xóa toàn bộ danh sách trong list
        //Lúc này dữ liệu =0 chứ ko bằng null
        chiKhoanModelList.clear();
        //Sau đó thêm lại từ đầu
        chiKhoanModelList.addAll(chiKhoanDao.getAllThu());
            chiKhoanAdapter.notifyDataSetChanged();
            Log.e("thukhoan","!=null");
    }
}
