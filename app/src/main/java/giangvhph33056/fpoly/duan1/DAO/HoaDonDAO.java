package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.time.LocalDate;
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
    public long thucHienMuaHang(int maSanPham, int soLuong, int maThanhVien) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long ketQua = -1; // Giá trị mặc định cho trường hợp thất bại
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();

        try {
            // Tạo một đối tượng ContentValues để chứa dữ liệu muốn chèn vào bảng HoaDon
            ContentValues giaTri = new ContentValues();

            // Đặt giá trị cho cột NgayDH, ở đây bạn cần thay thế "Định_dạng_Ngay_cua_ban" bằng định dạng ngày thực tế
            giaTri.put("NgayDH",day + "/" + month +"/" +year);

            // Đặt giá trị cho cột TrangThai, giả sử 1 biểu thị một giao dịch mua hàng thành công
            giaTri.put("TrangThai", 1);

            // Đặt giá trị cho cột SoLuong, được truyền vào từ tham số của phương thức
            giaTri.put("SoLuong", soLuong);

            // Tính tổng giá dựa trên giá và số lượng của sản phẩm
            int giaSanPham = layGiaSanPham(maSanPham, db);
            int tongGia = giaSanPham * soLuong;


            // Đặt giá trị cho cột Gia trong ContentValues
            giaTri.put("Gia", tongGia);

            // Đặt giá trị cho cột id trong ContentValues, là ID của thành viên thực hiện mua hàng
            giaTri.put("id", maThanhVien);

            // Đặt giá trị cho cột MaSP trong ContentValues, là ID của sản phẩm được mua
            giaTri.put("MaSP", maSanPham);

            // Chèn giao dịch mua hàng vào bảng HoaDon và nhận ID của giao dịch đó
            ketQua = db.insert("HoaDon", null, giaTri);
        } catch (Exception e) {
            // Ghi log nếu có lỗi xảy ra
            Log.i(TAG, "Lỗi", e);
        } finally {
            // Đảm bảo đóng kết nối với database trong mọi trường hợp
            if (db != null && db.isOpen()) {
                db.close();
            }
        }

        // Trả về ID của giao dịch mua hàng hoặc giá trị mặc định (-1) nếu có lỗi
        return ketQua;
    }

    // Phương thức hỗ trợ để lấy giá của một sản phẩm từ bảng SanPham
    private int layGiaSanPham(int maSanPham, SQLiteDatabase db) {
        // Truy vấn cơ sở dữ liệu để lấy giá của sản phẩm với mã sản phẩm được chỉ định
        Cursor cursor = db.query("SanPham", new String[]{"Gia"}, "MaSP = ?",
                new String[]{String.valueOf(maSanPham)}, null, null, null);

        // Khởi tạo giá trị mặc định cho giá
        int gia = 0;

        // Kiểm tra xem có dữ liệu trong Cursor hay không
        if (cursor != null && cursor.moveToFirst()) {
            // Lấy giá từ cột "Gia" trong Cursor
            gia = cursor.getInt(cursor.getColumnIndexOrThrow("Gia"));

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        // Trả về giá của sản phẩm
        return gia;
    }
    public boolean delete(int mapm) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long row = db.delete("HoaDon", "MaHD=?", new String[]{String.valueOf(mapm)});
        return (row > 0);
    }



}
