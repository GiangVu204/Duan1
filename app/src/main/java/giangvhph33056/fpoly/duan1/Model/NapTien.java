package giangvhph33056.fpoly.duan1.Model;

public class NapTien {
    private int MaNT ;
    private String AvataNT;
    private int sotien;
    private String ngayNT;
    private String TenNXN;
    private  int  trangthai;
    private  int id ;
    private int sotientrcdo;
    private String tenNNT;//ten tai khoan

    public NapTien() {
    }

    public int getSotientrcdo() {
        return sotientrcdo;
    }

    public void setSotientrcdo(int sotientrcdo) {
        this.sotientrcdo = sotientrcdo;
    }

//    public NapTien(int sotien, String avata, String ngayNT, String tenNXN, int trangthai, int id) {
//        this.sotien = sotien;
//        this.avata = avata;
//        this.ngayNT = ngayNT;
//        TenNXN = tenNXN;
//        this.trangthai = trangthai;
//        this.id = id;
//    }


    public NapTien(String avataNT, int sotien, String ngayNT, String tenNXN, int trangthai, int id) {
        AvataNT = avataNT;
        this.sotien = sotien;
        this.ngayNT = ngayNT;
        TenNXN = tenNXN;
        this.trangthai = trangthai;
        this.id = id;
    }

    public NapTien(int maNT, int sotien, String ngayNT, String tenNXN, int trangthai, String tenNNT) {
        MaNT = maNT;
        this.sotien = sotien;
        this.ngayNT = ngayNT;
        TenNXN = tenNXN;
        this.trangthai = trangthai;
        this.tenNNT = tenNNT;
    }

    public NapTien(int maNT, int sotien, String ngayNT, String tenNXN, int trangthai, int id, String tenNNT) {
        MaNT = maNT;
        this.sotien = sotien;
        this.ngayNT = ngayNT;
        TenNXN = tenNXN;
        this.trangthai = trangthai;
        this.id = id;
        this.tenNNT = tenNNT;
    }

    public int getMaNT() {
        return MaNT;
    }

    public void setMaNT(int maNT) {
        MaNT = maNT;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public String getAvataNT() {
        return AvataNT;
    }

    public void setAvataNT(String avataNT) {
        AvataNT = avataNT;
    }

    public String getNgayNT() {
        return ngayNT;
    }

    public void setNgayNT(String ngayNT) {
        this.ngayNT = ngayNT;
    }

    public String getTenNXN() {
        return TenNXN;
    }

    public void setTenNXN(String tenNXN) {
        TenNXN = tenNXN;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNNT() {
        return tenNNT;
    }

    public void setTenNNT(String tenNNT) {
        this.tenNNT = tenNNT;
    }
}
