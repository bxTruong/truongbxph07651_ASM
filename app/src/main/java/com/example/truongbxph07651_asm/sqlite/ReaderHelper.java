package com.example.truongbxph07651_asm.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReaderHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Thu_manager";

    //TABLE LOẠI THU
    public static final String TABLE_NAME_THU_LOAI="Thu_Loai";
    public static final String COLUMN_ID_THU="id_thu";
    public static final String COLUMN_NAME_THU="name_thu";

    //TABLE KHOẢN THU
    public static final String TABLE_NAME_THU_KHOAN="Thu_Khoan";
    public static final String COLUMN_ID_THU_KHOAN="id_thu_khoan";
    public static final String COLUMN_NAME_THU_KHOAN="name_thu_khoan";
    public static final String COLUMN_PRICE_THU_KHOAN="price_thu_khoan";
    public static final String COLUMN_DATE_THU_KHOAN="date_thu_khoan";
    public static final String COLUMN_LOAI_THU_KHOAN="loai_thu_khoan";

    //TABLE LOẠI CHI
    public static final String TABLE_NAME_CHI_LOAI="Chi_Loai";
    public static final String COLUMN_ID_CHI="id_chi";
    public static final String COLUMN_NAME_CHI="name_chi";

    //TABLE KHOẢN CHI
    public static final String TABLE_NAME_CHI_KHOAN="Chi_Khoan";
    public static final String COLUMN_ID_CHI_KHOAN="id_chi_khoan";
    public static final String COLUMN_NAME_CHI_KHOAN="name_chi_khoan";
    public static final String COLUMN_PRICE_CHI_KHOAN="price_chi_khoan";
    public static final String COLUMN_DATE_CHI_KHOAN="date_chi_khoan";
    public static final String COLUMN_LOAI_CHI_KHOAN="loai_chi_khoan";


    final String CREATE_THU_LOAI=" CREATE TABLE Thu_Loai ( id_thu INTEGER PRIMARY KEY , name_thu NVARCHAR  )";

    final String CREATE_THU_KHOAN=" CREATE TABLE Thu_Khoan ( id_thu_khoan INTEGER PRIMARY KEY , name_thu_khoan NVARCHAR , price_thu_khoan DOUBLE , date_thu_khoan VARCHAR , loai_thu_khoan NVARCHAR ) ";

    final String CREATE_CHI_LOAI=" CREATE TABLE Chi_Loai ( id_chi INTEGER PRIMARY KEY , name_chi NVARCHAR  )";

    final String CREATE_CHI_KHOAN=" CREATE TABLE Chi_Khoan ( id_chi_khoan INTEGER PRIMARY KEY , name_chi_khoan NVARCHAR , price_chi_khoan DOUBLE , date_chi_khoan VARCHAR , loai_chi_khoan NVARCHAR ) ";

    public ReaderHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_THU_LOAI);
        sqLiteDatabase.execSQL(CREATE_THU_KHOAN);
        sqLiteDatabase.execSQL(CREATE_CHI_LOAI);
        sqLiteDatabase.execSQL(CREATE_CHI_KHOAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
