package com.example.truongbxph07651_asm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.truongbxph07651_asm.model.ChiLoaiModel;
import com.example.truongbxph07651_asm.model.ThuLoaiModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.truongbxph07651_asm.sqlite.ReaderHelper.*;


public class ChiLoaiDao {
    private ReaderHelper readerHelper;

    public ChiLoaiDao(Context context){
        readerHelper =new ReaderHelper(context);
    }

    public void insertThu(ChiLoaiModel chiLoaiModel){
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_CHI, chiLoaiModel.getName());


        sqLiteDatabase.insert(TABLE_NAME_CHI_LOAI, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ChiLoaiModel> getAllThu() {
        List<ChiLoaiModel> chiLoaiModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT * FROM " + TABLE_NAME_CHI_LOAI;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ChiLoaiModel chiLoaiModel = new ChiLoaiModel();
                chiLoaiModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CHI)));
                chiLoaiModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CHI)));
                chiLoaiModelList.add(chiLoaiModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return chiLoaiModelList;
    }
    public int UpdateThu(ChiLoaiModel chiLoaiModel) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_CHI, chiLoaiModel.getName());

        //Update theo Id
        int update = sqLiteDatabase.update(TABLE_NAME_CHI_LOAI, contentValues, COLUMN_ID_CHI + " = ? ", new String[]{String.valueOf(chiLoaiModel.getId())});
        sqLiteDatabase.close();
        return update;
    }

    public int DeleteThu(int id) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME_CHI_LOAI, COLUMN_ID_CHI + " = ? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return delete;
    }
}
