package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.SanPham;

public class GioHangDAO {
    static Dbhelper dbhelper;
    public GioHangDAO (Context context){
        dbhelper = new Dbhelper(context);
    }
    public static ArrayList<SanPham> selectAllGioHang(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT sp.MaSP, sp.AvataSP ,sp.TenSP, sp.Gia, sp.SoLuong\n" +
                    "FROM SanPham sp\n",null);
            if(cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    SanPham sp = new SanPham();
                    sp.setMaSP(cursor.getInt(0));
                    sp.setAvataSP(cursor.getString(1));
                    sp.setTenSP(cursor.getString(2));
                    sp.setGia(cursor.getInt(3));
                    sp.setSoLuong(cursor.getInt(4));
                    list.add(sp);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
    // Phương thức thêm sản phẩm vào giỏ hàng
    public long themSanPhamVaoGioHang(String TenSP, int SoLuong, double Gia, String AvataSP) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AvataSP", AvataSP); // Thêm giá trị cho trường AvataSP
        values.put("TenSP", TenSP);
        values.put("SoLuong", SoLuong);
        values.put("Gia", Gia);

        return db.insert("SanPham", null, values);
    }
}
