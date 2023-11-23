package giangvhph33056.fpoly.duan1.Model;

public class SanPham {
    private int MaSP;
    private String TenSP;
    private  int Gia;
    private  int SoLuong;
    private  int MaKT;
    private  int MaTH;
    private  int MaLSP;
/// thêm
    private String size;
    private String tenthuonghieu;
    private String tenlsp;
    public SanPham() {
    }
// them

    public SanPham(String tenSP, int gia) {
        TenSP = tenSP;
        Gia = gia;
    }

    public SanPham(int maSP, String tenSP, int gia, int soLuong, String size, String tenthuonghieu, String tenlsp) {
        MaSP = maSP;
        TenSP = tenSP;
        Gia = gia;
        SoLuong = soLuong;
        this.size = size;
        this.tenthuonghieu = tenthuonghieu;
        this.tenlsp = tenlsp;
    }

    //goc
    public SanPham(int maSP, String tenSP, int gia, int soLuong, int maKT, int maTH, int maLSP) {
        MaSP = maSP;
        TenSP = tenSP;
        Gia = gia;
        SoLuong = soLuong;
        MaKT = maKT;
        MaTH = maTH;
        MaLSP = maLSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTenthuonghieu() {
        return tenthuonghieu;
    }

    public void setTenthuonghieu(String tenthuonghieu) {
        this.tenthuonghieu = tenthuonghieu;
    }

    public String getTenlsp() {
        return tenlsp;
    }

    public void setTenlsp(String tenlsp) {
        this.tenlsp = tenlsp;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getMaKT() {
        return MaKT;
    }

    public void setMaKT(int maKT) {
        MaKT = maKT;
    }

    public int getMaTH() {
        return MaTH;
    }

    public void setMaTH(int maTH) {
        MaTH = maTH;
    }

    public int getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(int maLSP) {
        MaLSP = maLSP;
    }
}
