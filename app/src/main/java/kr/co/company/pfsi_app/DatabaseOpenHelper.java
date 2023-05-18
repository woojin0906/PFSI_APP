package kr.co.company.pfsi_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String tableName = "UserInfo";

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB_create","db 생성_db가 없을때만 최초로 실행함");
        createTable(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
    }
    public void createTable(SQLiteDatabase db){
        String sql = "CREATE TABLE " +tableName+ "(intro text, name text, birth text, phone text, address text, gardianPhone text)";
        try {
            db.execSQL(sql);
        }catch (SQLException e){
        }
    }

    public Cursor selectInfo(SQLiteDatabase db) {
        Log.i("DB_select", "회원 정보 조회");
        String sql = "SELECT * FROM " + tableName;
        return db.rawQuery(sql, null);
    }

    public void insertInfo(SQLiteDatabase db, String intro, String name, String birth, String phone, String address, String gardianPhone){
        Log.i("DB_insert","회원 정보 업데이트");
        db.beginTransaction();
        try {
            String sql = "INSERT INTO " +tableName+ " values('"+ intro +"', '"+ name +"', '"+birth+"', '"+phone+"','"+address+"','"+gardianPhone+"')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public void deleteInfo(SQLiteDatabase db, String phone){
        Log.i("DB_delete","회원 정보 제거");
        db.beginTransaction();
        try {
            String sql2 = "DELETE FROM " +tableName+ " WHERE phone = '"+phone+"'";
            db.execSQL(sql2);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

}