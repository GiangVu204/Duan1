package giangvhph33056.fpoly.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;

public class ThuongHieuDAO {

    Dbhelper dbhelper;

    public ThuongHieuDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<ThuongHieu> getDSThuongHieu(){
        ArrayList<ThuongHieu> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT th.MaTH, th.TenTH from ThuongHieu th", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
                do {
                    list.add(new ThuongHieu(cursor.getInt(0), cursor.getString(1)));
                } while (cursor.moveToNext());
        }
        return list;
    }

    public boolean insert (String TenTH){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenTH", TenTH);
        long check = db.insert("ThuongHieu", null, values);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean update(int MaTH, String TenTH){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenTH", TenTH);
        long check = db.update("ThuongHieu", values, "MaTH = ?", new String[]{String.valueOf(MaTH)});
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }

    // xóa loại sách
    // 1: xóa thành công, 0: xóa thất bại, -1 : có sách tồn tại trong loại đó
    public int delete(int MaTH){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham WHERE MaTH = ?", new String[]{String.valueOf(MaTH)});
        if(cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("ThuongHieu","MaTH = ?", new String[]{String.valueOf(MaTH)});
        if(check == -1){
            return 0;
        }else {
            return 1;
        }
    }


}
