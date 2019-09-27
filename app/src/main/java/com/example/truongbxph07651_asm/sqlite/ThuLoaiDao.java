package com.example.truongbxph07651_asm.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.truongbxph07651_asm.model.ThuLoaiModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.truongbxph07651_asm.sqlite.ReaderHelper.*;

public class ThuLoaiDao {
    private ReaderHelper readerHelper;

    public ThuLoaiDao(Context context){
        readerHelper =new ReaderHelper(context);
    }

    public void insertThu(ThuLoaiModel thuLoaiModel){
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_THU, thuLoaiModel.getName());


        sqLiteDatabase.insert(TABLE_NAME_THU_LOAI, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ThuLoaiModel> getAllThu() {
        List<ThuLoaiModel> thuModelList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        String SELECT = " SELECT * FROM " + TABLE_NAME_THU_LOAI;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                ThuLoaiModel thuLoaiModel = new ThuLoaiModel();
                thuLoaiModel.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_THU)));
                thuLoaiModel.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_THU)));
                thuModelList.add(thuLoaiModel);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return thuModelList;
    }
    public int UpdateThu(ThuLoaiModel thuLoaiModel) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME_THU, thuLoaiModel.getName());

        //Update theo Id
        int update = sqLiteDatabase.update(TABLE_NAME_THU_LOAI, contentValues, COLUMN_ID_THU + " = ? ", new String[]{String.valueOf(thuLoaiModel.getId())});
        sqLiteDatabase.close();
        return update;
    }

    public int DeleteThu(int id) {
        SQLiteDatabase sqLiteDatabase = readerHelper.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME_THU_LOAI, COLUMN_ID_THU + " = ? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return delete;
    }

}
