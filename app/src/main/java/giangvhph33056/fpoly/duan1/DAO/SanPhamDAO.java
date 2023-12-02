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
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class SanPhamDAO {
    static Dbhelper dbhelper;
    public SanPhamDAO (Context context){
        dbhelper = new Dbhelper(context);
    }

    public static ArrayList<SanPham> selectAllSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT sp.MaSP, sp.AvataSP ,sp.TenSP, sp.Gia,sp.SoLuong,sp.id,kt.MaKT,kt.Size,kt.SoLuong,sp.MaTH,th.SDT,th.TenTH,sp.MaLSP,lsp.TenLSP\n" +
                    "FROM SanPham sp, KichThuoc kt,ThuongHieu th, LoaiSanPham lsp\n" +
                    "Where sp.id = kt.id and sp.MaTH = th.MaTH and  sp.MaLSP = lsp.MaLSP",null);
            if(cursor.getCount() >0 ){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    SanPham sp = new SanPham();
                    sp.setMaSP(cursor.getInt(0));
                    sp.setAvataSP(cursor.getString(1));
                    sp.setTenSP(cursor.getString(2));
                    sp.setGia(cursor.getInt(3));
                    sp.setSoLuong(cursor.getInt(4));
                    sp.setSize(cursor.getString(7));
                    sp.setTenthuonghieu(cursor.getString(11));
                    sp.setTenlsp(cursor.getString(13));
                    list.add(sp);
                    cursor.moveToNext();

                }
            }
        }catch (Exception e){
            Log.i(TAG,"Lỗi",e);
        }
        return list;
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



    public int delete(int MaLSP){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HoaDon WHERE MaSP = ?", new String[]{String.valueOf(MaLSP)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("SanPham", "MaSP = ?", new String[]{String.valueOf(MaLSP)});
        if (check == -1){
            return 0;
        }else {
            return 1;
        }
    }
    public boolean deletee(int masp) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("SanPham", "MaSP=?", new String[]{String.valueOf(masp)});
        return (row > 0);
    }
    public boolean insert(SanPham pm) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("MaSP", pm.getMaLSP());
        values.put("AvataSP",pm.getAvataSP());
        values.put("TenSP", pm.getTenSP());
        values.put("Gia",pm.getGia());
        values.put("SoLuong", pm.getSoLuong());
        values.put("id", pm.getId());
        values.put("MaTH", pm.getMaTH());
        values.put("MaLSP", pm.getMaLSP());
        long row = db.insert("SanPham", null, values);
        return (row > 0);
    }

    public boolean update(SanPham pm){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSP", pm.getMaLSP());
        values.put("AvataSP",pm.getAvataSP());
        values.put("TenSP", pm.getTenSP());
        values.put("Gia",pm.getGia());
        values.put("SoLuong", pm.getSoLuong());
        values.put("id", pm.getId());
        values.put("MaTH", pm.getMaTH());
        values.put("MaLSP", pm.getMaLSP());
        long row = db.update("SanPham", values, "id=?", new String[]{String.valueOf(pm.getId())});
        return (row > 0);
    }

    // Hàm cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateQuantity(int maSP, int newQuantity) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SoLuong", newQuantity);

        // Update số lượng sản phẩm trong giỏ hàng dựa trên MaSP
        long row = db.update("SanPham", values, "MaSP=?", new String[]{String.valueOf(maSP)});
        return (row > 0);
    }

    // Hàm lấy số lượng của một sản phẩm trong giỏ hàng dựa trên MaSP
    public int getQuantity(int maSP) {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        int quantity = 0;

        Cursor cursor = db.rawQuery("SELECT SoLuong FROM SanPham WHERE MaSP = ?", new String[]{String.valueOf(maSP)});
        if (cursor.moveToFirst()) {
            // In log để xem thông tin chi tiết của Cursor
            Log.d("CursorInfo", "ColumnIndex for SoLuong: " + cursor.getColumnIndex("SoLuong"));
            // Sử dụng chỉ mục cố định của cột "SoLuong" (ví dụ 0 là chỉ mục đầu tiên)
            quantity = cursor.getInt(0);
        }

        cursor.close();
        return quantity;
    }

}
