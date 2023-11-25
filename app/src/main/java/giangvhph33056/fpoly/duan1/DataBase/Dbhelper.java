package giangvhph33056.fpoly.duan1.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "THOIDAISHOP";
    private static final int DB_VERSION= 28;

    public Dbhelper(Context context) {
        super(context, DB_NAME,null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ThanhVien(\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    MaTV    TEXT NOT NULL,\n" +
                "    HoTen   TEXT NOT NULL,\n" +
                "    MatKhau TEXT NOT NULL,\n" +
                "    SDT     INTEGER NOT NULL,\n" +
                "    Email TEXT NOT NULL,\n" +
                "    DChi TEXT NOT NULL,\n" +
                "    Loai TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE ThuongHieu(\n" +
                "    MaTH    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    Anh     TEXT NOT NULL,\n" +
                "    SDT     INTEGER NOT NULL,\n" +
                "    TenTH   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE LoaiSanPham(\n" +
                "    MaLSP    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    TenLSP   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE KichThuoc(\n" +
                "    MaKT    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    Size   INTEGER NOT NULL,\n" +
                "    SoLuong INTEGER NOT NULL\n" +
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
                "    id    INTEGER REFERENCES ThanhVien (id), \n" +
                "    MaSP    INTEGER REFERENCES SanPham (MaSP) \n" +
                ");\n");
        //Thủ Thư
        db.execSQL("INSERT INTO  ThanhVien VALUES(1,'admin','Lê Hoàng Tú','admin',0982322079,'lehoangtu56@gmail.com','hà nội','ADMIN'),(2,'nhanvien','Nguyễn Văn A','nhanvien',0982322079,'Nhanvien56@gmail.com','hà nội','Nhân Viên'),(3,'khanghang','Sùng A Pháo','khachhang',0982322079,'Khachhang56@gmail.com','hà nội','Khách Hàng')");
        db.execSQL("INSERT INTO  ThuongHieu VALUES(1, 'anh1', 0982322079, 'Nike'), (2, 'anh2', 0868761723, 'Adidas'), (3, 'anh3', 0734423235, 'Converse')");
        db.execSQL("INSERT INTO  LoaiSanPham VALUES(1, 'Nike 1'), (2, 'Adidas 1'), (3, 'Nike 2')");
        db.execSQL("INSERT INTO  KichThuoc VALUES(1, 39, 3), (2, 40, 6), (3, 41, 8)");
        db.execSQL("INSERT INTO  SanPham VALUES(0,'Giày Thể Thao', 3000,3,1,1,1),(1,'Giày A', 3000,3,1,2,1),(2,'Giay b', 3000,3,1,3,1),(3,'Giay c', 3000,3,1,2,1)");
        db.execSQL("INSERT INTO  HoaDon VALUES(1,'20/04/2004', 1, 13, 9000, 1, 2),(2,'20/04/2004', 1, 13, 9000, 1, 3)");
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
