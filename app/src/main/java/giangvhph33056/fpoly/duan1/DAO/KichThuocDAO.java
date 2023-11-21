package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class KichThuocDAO {
    Dbhelper dbhelper;


    public KichThuocDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<KichThuoc> selectAllKichThuoc(){
        ArrayList<KichThuoc> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from KichThuoc",null);
            if(cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    KichThuoc kt = new KichThuoc();
                    kt.setMaKT(cursor.getInt(0));
                    kt.setSize(cursor.getInt(1));
                    kt.setSoLuong(cursor.getInt(2));
                    list.add(kt);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
    public int delete(int MaKT) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM SanPham WHERE MaKT=? ",new String[]{String.valueOf(MaKT)});
        if (cursor.getCount()!=0){
            return -1;
        }else{
            long check = db.delete("KichThuoc", "MaKT=?", new String[]{String.valueOf(MaKT)});
            if (check ==-1){
                return 0;
            }else{
                return 1;
            }
        }

    }
    public  boolean update(KichThuoc kt){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Size",kt.getSize());
        values.put("SoLuong",kt.getSoLuong());
        long row = db.update("KichThuoc", values, "MaKT=?", new String[]{String.valueOf(kt.getMaKT())});
        return (row > 0);
    }
}