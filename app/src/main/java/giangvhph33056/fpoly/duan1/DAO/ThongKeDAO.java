package giangvhph33056.fpoly.duan1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import giangvhph33056.fpoly.duan1.DataBase.Dbhelper;

public class ThongKeDAO {
    Dbhelper dbhelper;
    public ThongKeDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }


    public int getDoanhThu(String start, String end) {
        start = start.replace("/", "");
        end = end.replace("/", "");
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Gia) FROM HoaDon WHERE substr(NgayDH, 7) || substr(NgayDH, 4,2) || substr(NgayDH,1,2) BETWEEN ? AND ?", new String[]{start, end});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }

}
