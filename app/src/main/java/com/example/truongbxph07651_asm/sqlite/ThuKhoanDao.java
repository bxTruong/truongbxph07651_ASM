package com.example.truongbxph07651_asm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.truongbxph07651_asm.fragment.ThongKe.ThongKeFragment;
import com.example.truongbxph07651_asm.model.ThongKeModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.truongbxph07651_asm.sqlite.ReaderHelper.*;

public class ThuKhoanDao {
    private ReaderHelper readerHelper;

    public ThuKhoanDao(Context context) {
        readerHelper = new ReaderHelper(context);
    }

    public void insertThu(ThuKhoanModel thuKhoanModel) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_THU_KHOAN, thuKhoanModel.getName());
        contentValues.put(COLUMN_PRICE_THU_KHOAN, thuKhoanModel.getPrice());
        contentValues.put(COLUMN_DATE_THU_KHOAN, thuKhoanModel.getDate());
        contentValues.put(COLUMN_LOAI_THU_KHOAN, thuKhoanModel.getLoaithu());

        sqLiteDatabase.insert(TABLE_NAME_THU_KHOAN, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ThuKhoanModel> getAllThu() {
        List<ThuKhoanModel> thuKhoanModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT * FROM " + TABLE_NAME_THU_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ThuKhoanModel thuKhoanModel = new ThuKhoanModel();
                thuKhoanModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_THU_KHOAN)));
                thuKhoanModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_THU_KHOAN)));
                thuKhoanModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_THU_KHOAN)));
                thuKhoanModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_THU_KHOAN)));
                thuKhoanModel.setLoaithu(cursor.getString(cursor.getColumnIndex(COLUMN_LOAI_THU_KHOAN)));
                thuKhoanModelList.add(thuKhoanModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return thuKhoanModelList;
    }

    public int UpdateThu(ThuKhoanModel thuKhoanModel) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_THU_KHOAN, thuKhoanModel.getName());
        contentValues.put(COLUMN_PRICE_THU_KHOAN, thuKhoanModel.getPrice());
        contentValues.put(COLUMN_DATE_THU_KHOAN, thuKhoanModel.getDate());
        contentValues.put(COLUMN_LOAI_THU_KHOAN, thuKhoanModel.getLoaithu());

        //Update theo Id
        int update = sqLiteDatabase.update(TABLE_NAME_THU_KHOAN, contentValues, COLUMN_ID_THU_KHOAN + " = ? ", new String[]{String.valueOf(thuKhoanModel.getId())});
        sqLiteDatabase.close();
        return update;
    }

    public int DeleteThu(int id) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME_THU_KHOAN, COLUMN_ID_THU_KHOAN + " = ? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return delete;
    }

    public double tongThu() {
        double tongThu = 0;
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT  SUM( " + (COLUMN_PRICE_THU_KHOAN) + " )  FROM " + TABLE_NAME_THU_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                tongThu = cursor.getDouble(0);
            }
            while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return tongThu;
    }

    public double tongChi() {
        double tongChi = 0;
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT  SUM( " + (COLUMN_PRICE_CHI_KHOAN) + " )  FROM " + TABLE_NAME_CHI_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                tongChi = cursor.getDouble(0);
            }
            while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return tongChi;
    }
    public List<ThongKeModel> tongThuNgay() {
        List<ThongKeModel> thongKeModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT SUM ( " + (COLUMN_PRICE_THU_KHOAN) + " ) AS aa , " + COLUMN_DATE_THU_KHOAN + " FROM " + TABLE_NAME_THU_KHOAN + " GROUP BY " + COLUMN_DATE_THU_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ThongKeModel thongKeModel = new ThongKeModel();
                thongKeModel.setTong(cursor.getDouble(cursor.getColumnIndex("aa")));
                thongKeModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_THU_KHOAN)));
                thongKeModelList.add(thongKeModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return thongKeModelList;
    }

    public List<ThongKeModel> tongChiNgay() {
        List<ThongKeModel> thongKeModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT  SUM ( " + (COLUMN_PRICE_CHI_KHOAN) + " ) AS bb , " + COLUMN_DATE_CHI_KHOAN + " FROM " + TABLE_NAME_CHI_KHOAN + " GROUP BY " + COLUMN_DATE_CHI_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ThongKeModel thongKeModel = new ThongKeModel();
                thongKeModel.setTong(cursor.getDouble(cursor.getColumnIndex("bb")));
                thongKeModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CHI_KHOAN)));
                thongKeModelList.add(thongKeModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return thongKeModelList;
    }

}
