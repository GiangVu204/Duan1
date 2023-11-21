package giangvhph33056.fpoly.duan1.Model;

public class ThuongHieu {
    int MaTH, SDT;
    String TenTH;

    public ThuongHieu() {
    }

    public ThuongHieu(int maTH, int SDT, String tenTH) {
        MaTH = maTH;
        this.SDT = SDT;
        TenTH = tenTH;
    }

    public int getMaTH() {
        return MaTH;
    }

    public void setMaTH(int maTH) {
        MaTH = maTH;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getTenTH() {
        return TenTH;
    }

    public void setTenTH(String tenTH) {
        TenTH = tenTH;
    }
}
