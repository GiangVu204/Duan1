package giangvhph33056.fpoly.duan1.DAO;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;

public class LoaiSanPhamDAO {

    Dbhelper dbhelper;

    public LoaiSanPhamDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    public ArrayList<LoaiSanPham> getDSLoaiSanPham(){
        ArrayList<LoaiSanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM LoaiSanPham", null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    LoaiSanPham lsp = new LoaiSanPham();
                    lsp.setMaLSP(cursor.getInt(0));
                    lsp.setTenLSP(cursor.getString(1));
                    list.add(lsp);
                    cursor.moveToNext();
                }
            }

        }catch (Exception e){
            Log.e(TAG,"Lỗi", e);
        }
        return list;
    }


    public int delete(int MaLSP){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LoaiSanPham WHERE MaLSP = ?", new String[]{String.valueOf(MaLSP)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = db.delete("LoaiSanPham", "MaLSP = ?", new String[]{String.valueOf(MaLSP)});
        if (check == -1){
            return 0;
        }else {
            return 1;
        }
    }
}
