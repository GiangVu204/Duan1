package giangvhph33056.fpoly.duan1.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "THOIDAISHOP";
    private static final int DB_VERSION= 42;

    public Dbhelper(Context context) {
        super(context, DB_NAME,null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ThanhVien(\n" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    MaTV     TEXT NOT NULL,\n" +
                "    AvataTV  TEXT NOT NULL,\n" +
                "    HoTen    TEXT NOT NULL,\n" +
                "    MatKhau  TEXT NOT NULL,\n" +
                "    SDT      TEXT NOT NULL,\n" +
                "    Email    TEXT NOT NULL,\n" +
                "    DChi     TEXT NOT NULL,\n" +
                "    SoTien   INTEGER NOT NULL,\n" +
                "    Loai     TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE ThuongHieu(\n" +
                "    MaTH    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    Anh     TEXT NOT NULL,\n" +
                "    SDT     TEXT NOT NULL,\n" +
                "    TenTH   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE LoaiSanPham(\n" +
                "    MaLSP    INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    Avata    TEXT NOT NULL,\n" +
                "    TenLSP   TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE KichThuoc(\n" +
                "    id       INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    AvataKT  TEXT NOT NULL,\n" +
                "    MaKT     TEXT NOT NULL,\n" +
                "    Size     INTEGER NOT NULL,\n" +
                "    SoLuong  INTEGER NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE GioHang(\n" +
                "    MAGH       INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    id        INTEGER REFERENCES ThanhVien (id), \n" +
                "    MaSP      INTEGER REFERENCES SanPham (MaSP), \n" +
                "    SoLuong  INTEGER NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE DONHANG(\n" +
                "    madonhang       INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    id               INTEGER REFERENCES ThanhVien (id), \n" +
                "    ngaydathang      TEXT NOT NULL, \n" +
                "    tongtien          INTEGER NOT NULL,\n" +
                "    trangthai          TEXT NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE CHITIETDONHANG(\n" +
                "    machitietdonhang   INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    madonhang          INTEGER REFERENCES DONHANG (madonhang), \n" +
                "    MaSP               INTEGER REFERENCES SanPham (MaSP), \n" +
                "    soluong            INTEGER NOT NULL,\n" +
                "    dongia             INTEGER NOT NULL,\n" +
                "    thanhtien          INTEGER NOT NULL\n" +
                ");\n");
        db.execSQL("CREATE TABLE SanPham(\n" +
                "    MaSP     INTEGER PRIMARY KEY  AUTOINCREMENT,\n" +
                "    AvataSP  TEXT NOT NULL,\n" +
                "    TenSP    TEXT NOT NULL,\n" +
                "    Gia      INTEGER NOT NULL,\n" +
                "    SoLuong  INTEGER NOT NULL,\n" +
                "    id       INTEGER REFERENCES KichThuoc (id), \n" +
                "    MaTH     INTEGER REFERENCES ThuongHieu (MaTH), \n" +
                "    MaLSP    INTEGER REFERENCES LoaiSanPham (MaLSP) \n" +
                ");\n");

        //INSERT
        db.execSQL("INSERT INTO  ThanhVien VALUES(1,'admin', 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','Lê Hoàng Tú','admin',0982322079,'lehoangtu56@gmail.com','hà nội',100000,'ADMIN')," +
                "(2,'nhanvien', 'Avata 2','Nguyễn Văn A','nhanvien',0982322079,'Nhanvien56@gmail.com','hà nội',100000,'Nhân Viên')," +
                "(3,'khanghang', 'Avata 3','Sùng A Pháo','khachhang',0982322079,'Khachhang56@gmail.com','hà nội',1000000,'Khách Hàng')");
       db.execSQL("INSERT INTO  ThuongHieu VALUES(1, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 0982322079, 'Nike')" +
               ", (2, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 0868761723, 'Adidas'), " +
               "(3, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 0734423235, 'Converse')");
       db.execSQL("INSERT INTO  LoaiSanPham VALUES(1, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 'Nike 1')," +
               " (2, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 'Adidas 1'), " +
               "(3, 'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 'Nike 2')");
      db.execSQL("INSERT INTO  KichThuoc VALUES(1,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','CS39', 39, 3)," +
              " (2,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180', 'AD40',40, 6), " +
              "(3,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','NK41', 41, 8)");
      db.execSQL("INSERT INTO  SanPham VALUES(0,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','Giày Thể Thao', 3000,3,1,1,1)," +
              "(1,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','Giày A', 3000,3,1,2,1)," +
              "(2,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','Giay b', 3000,3,1,3,1)," +
              "(3,'https://tse4.mm.bing.net/th?id=OIP.K0haaJczxVOugK9Bk3ZCVwHaJQ&pid=Api&P=0&h=180','Giay c', 3000,3,1,2,1)");

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
            db.execSQL("DROP TABLE IF EXISTS DONHANG");
            db.execSQL("DROP TABLE IF EXISTS CHITIETDONHANG");
            db.execSQL("DROP TABLE IF EXISTS GioHang");
            // tạo bảng mới
            onCreate(db);
        }
    }
}
