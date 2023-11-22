package giangvhph33056.fpoly.duan1.Model;

public class ThanhVien {
    private String MaTV;
    private String HoTen;
    private String MatKhau;
    private int SDT;
    private String Email;
    private String DChi;

    public ThanhVien() {
    }

    public ThanhVien(String maTV, String hoTen, String matKhau, int SDT, String email, String DChi) {
        MaTV = maTV;
        HoTen = hoTen;
        MatKhau = matKhau;
        this.SDT = SDT;
        Email = email;
        this.DChi = DChi;
    }
// hien thi
    public ThanhVien(String maTV, String hoTen, int SDT, String email, String DChi) {
        MaTV = maTV;
        HoTen = hoTen;
        this.SDT = SDT;
        Email = email;
        this.DChi = DChi;
    }

    public String getMaTV() {
        return MaTV;
    }

    public void setMaTV(String maTV) {
        MaTV = maTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDChi() {
        return DChi;
    }

    public void setDChi(String DChi) {
        this.DChi = DChi;
    }
}
