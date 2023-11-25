package giangvhph33056.fpoly.duan1.Model;

public class LoaiSanPham {

    int MaLSP;
    String TenLSP;

    public LoaiSanPham() {
    }

    public LoaiSanPham(int maLSP, String tenLSP) {
        MaLSP = maLSP;
        TenLSP = tenLSP;
    }

    public int getMaLSP() {
        return MaLSP;
    }

    public void setMaLSP(int maLSP) {
        MaLSP = maLSP;
    }

    public String getTenLSP() {
        return TenLSP;
    }

    public void setTenLSP(String tenLSP) {
        TenLSP = tenLSP;
    }
}
