package giangvhph33056.fpoly.duan1.Model;

public class HoaDon {
    private int MaHD;
    private String NgayDH;
    private int TrangThai;
    private int SoLuong;
    private int Gia;
    private int id;
    private int MaSP;

    private String tentv;
    private String tensp;

    public HoaDon() {
    }

    public HoaDon(int maHD, String ngayDH, int trangThai, int soLuong, int gia, String tentv, String tensp) {
        MaHD = maHD;
        NgayDH = ngayDH;
        TrangThai = trangThai;
        SoLuong = soLuong;
        Gia = gia;
        this.tentv = tentv;
        this.tensp = tensp;
    }

    public HoaDon(int maHD, String ngayDH, int trangThai, int soLuong, int gia, int id) {
        MaHD = maHD;
        NgayDH = ngayDH;
        TrangThai = trangThai;
        SoLuong = soLuong;
        Gia = gia;
        this.id = id;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public String getNgayDH() {
        return NgayDH;
    }

    public void setNgayDH(String ngayDH) {
        NgayDH = ngayDH;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTentv() {
        return tentv;
    }

    public void setTentv(String tentv) {
        this.tentv = tentv;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
}
