package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.HoaDon;
import giangvhph33056.fpoly.duan1.Model.SanPham;

public class HoaDonDAO {
    static Dbhelper dbhelper;
    public HoaDonDAO (Context context){
        dbhelper = new Dbhelper(context);
    }
    public static ArrayList<HoaDon> selectAllHoaDon(){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT hd.MaHD ,hd.NgayDH, hd.TrangThai,hd.SoLuong,hd.Gia,hd.id, tv.MaTV, tv.HoTen,tv.MatKhau,tv.SDT,tv.Email,tv.DChi,tv.Loai,hd.MaSP, sp.TenSP, sp.Gia,sp.SoLuong\n" +
                    "FROM HoaDon hd, ThanhVien tv, SanPham sp\n" +
                    "Where hd.id = tv.id and hd.MaSP = sp.MaSP",null);
            if(cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(cursor.getInt(0));
                    hd.setNgayDH(cursor.getString(1));
                    hd.setTrangThai(cursor.getInt(2));
                    hd.setSoLuong(cursor.getInt(3));
                    hd.setGia(cursor.getInt(4));
                    hd.setTentv(cursor.getString(7));
                    hd.setTensp(cursor.getString(14));
                    list.add(hd);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
    }
    public boolean delete(int mapm) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("HoaDon", "MaHD=?", new String[]{String.valueOf(mapm)});
        return (row > 0);
    }
}
