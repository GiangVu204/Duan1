package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class ThanhVienDAO {
    Dbhelper dbhelper;
    SharedPreferences sharedPreferences;

    public ThanhVienDAO(Context context) {
        dbhelper = new Dbhelper(context);
        sharedPreferences = context.getSharedPreferences("DANGNHAPTV", Context.MODE_PRIVATE);
    }

    public boolean checklogin(String MaTV, String MatKhau) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ThanhVien where MaTV=? and  MatKhau=?", new String[]{MaTV, MatKhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("MaTV", cursor.getString(0));
            editor.putString("HoTen", cursor.getString(1));
            editor.putString("MatKhau", cursor.getString(2));
            // Lưu mật khẩu vào SharedPreferences
           // editor.putString("Loai", cursor.getString(3));
            editor.commit();
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<ThanhVien> selectAllthanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from ThanhVien",null);
            if(cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    ThanhVien tv = new ThanhVien();
                    tv.setMaTV(cursor.getString(0));
                    tv.setHoTen(cursor.getString(1));
                    tv.setMatKhau(cursor.getString(2));
                    tv.setSDT(cursor.getInt(3));
                    tv.setEmail(cursor.getString(4));
                    tv.setDChi(cursor.getString(5));
                    list.add(tv);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
}
