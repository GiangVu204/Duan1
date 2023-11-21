package giangvhph33056.fpoly.duan1.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "THOIDAISHOP";
    private static final int DB_VERSION= 3;

    public Dbhelper(Context context) {
        super(context, DB_NAME,null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ThanhVien(\n" +
                "    MaTV    TEXT PRIMARY KEY,\n" +
                "    HoTen   TEXT NOT NULL,\n" +
                "    MatKhau TEXT NOT NULL,\n" +
                "    SDT     INTEGER NOT NULL,\n" +
                "    Email TEXT NOT NULL,\n" +
                "    DChi TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE ThuongHieu(\n" +
                "    MaTH    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    SDT     INTEGER NOT NULL,\n" +
                "    TenTH   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE LoaiSanPham(\n" +
                "    MaLSP    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    TenLSP   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE KichThuoc(\n" +
                "    MaKT    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    KichThuoc   TEXT NOT NULL,\n" +
                "    SoLuong TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE SanPham(\n" +
                "    MaSP    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    TenSP   TEXT NOT NULL,\n" +
                "    Gia     INTEGER NOT NULL,\n" +
                "    SoLuong     INTEGER NOT NULL,\n" +
                "    MaKT    INTEGER REFERENCES KichThuoc (MaKT), \n" +
                "    MaTH    INTEGER REFERENCES ThuongHieu (MaTH), \n" +
                "    MaLSP    INTEGER REFERENCES LoaiSanPham (MaLSP) \n" +
                ");\n");
        db.execSQL("CREATE TABLE HoaDon(\n" +
                "    MaHD    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    NgayDH   TEXT NOT NULL,\n" +
                "    TrangThai     INTEGER NOT NULL,\n" +
                "    SoLuong     INTEGER NOT NULL,\n" +
                "    Gia     INTEGER NOT NULL,\n" +
                "    MaTV    TEXT REFERENCES ThanhVien (MaTV), \n" +
                "    MaSP    INTEGER REFERENCES SanPham (MaSP) \n" +
                ");\n");
        //Thủ Thư
        db.execSQL("INSERT INTO  ThanhVien VALUES('admin','Lê Hoàng Tú','admin',0982322079,'lehoangtu56@gmail.com','hà nội')");
        db.execSQL("INSERT INTO  ThuongHieu VALUES(1, 0982322079, 'Nike'), (2, 0868761723, 'Adidas')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            // Drop các bảng cũ (nếu tang version)
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS ThuongHieu");
            db.execSQL("DROP TABLE IF EXISTS LoaiSanPham");
            db.execSQL("DROP TABLE IF EXISTS KichThuoc");
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            db.execSQL("DROP TABLE IF EXISTS HoaDon");
            // tạo bảng mới
            onCreate(db);
        }
    }
}
