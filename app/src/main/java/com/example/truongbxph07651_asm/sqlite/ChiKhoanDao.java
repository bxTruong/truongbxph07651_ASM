package com.example.truongbxph07651_asm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.truongbxph07651_asm.model.ChiKhoanModel;
import com.example.truongbxph07651_asm.model.ThuKhoanModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.truongbxph07651_asm.sqlite.ReaderHelper.*;



public class ChiKhoanDao {
    private ReaderHelper readerHelper;

    public ChiKhoanDao(Context context){
        readerHelper =new ReaderHelper(context);
    }

    public void insertThu(ChiKhoanModel chiKhoanModel){
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_CHI_KHOAN, chiKhoanModel.getName());
        contentValues.put(COLUMN_PRICE_CHI_KHOAN, chiKhoanModel.getPrice());
        contentValues.put(COLUMN_DATE_CHI_KHOAN, chiKhoanModel.getDate());
        contentValues.put(COLUMN_LOAI_CHI_KHOAN, chiKhoanModel.getLoaichi());


        sqLiteDatabase.insert(TABLE_NAME_CHI_KHOAN, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ChiKhoanModel> getAllThu() {
        List<ChiKhoanModel> chiKhoanModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT * FROM " + TABLE_NAME_CHI_KHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ChiKhoanModel chiKhoanModel = new ChiKhoanModel();
                chiKhoanModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CHI_KHOAN)));
                chiKhoanModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHI_KHOAN)));
                chiKhoanModel.setPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE_CHI_KHOAN)));
                chiKhoanModel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_CHI_KHOAN)));
                chiKhoanModel.setLoaichi(cursor.getString(cursor.getColumnIndex(COLUMN_LOAI_CHI_KHOAN)));
                chiKhoanModelList.add(chiKhoanModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return chiKhoanModelList;
    }
    public int UpdateThu(ChiKhoanModel chiKhoanModel) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_CHI_KHOAN, chiKhoanModel.getName());
        contentValues.put(COLUMN_PRICE_CHI_KHOAN, chiKhoanModel.getPrice());
        contentValues.put(COLUMN_DATE_CHI_KHOAN, chiKhoanModel.getDate());
        contentValues.put(COLUMN_LOAI_CHI_KHOAN, chiKhoanModel.getLoaichi());

        //Update theo Id
        int update = sqLiteDatabase.update(TABLE_NAME_CHI_KHOAN, contentValues, COLUMN_ID_CHI_KHOAN + " = ? ", new String[]{String.valueOf(chiKhoanModel.getId())});
        sqLiteDatabase.close();
        return update;
    }

    public int DeleteThu(int id) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME_CHI_KHOAN, COLUMN_ID_CHI_KHOAN + " = ? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return delete;
    }
}
