package giangvhph33056.fpoly.duan1.Model;

public class ThanhVien {
    private  int id;
    private String MaTV;
    private String AvataTV;
    private String HoTen;
    private String MatKhau;
    private int SDT;
    private String Email;
    private String DChi;
    private String loai;

    public ThanhVien() {
    }
/// add
    ///
    public ThanhVien(String maTV, String avataTV, String hoTen, String matKhau, int SDT, String email, String DChi, String loai) {
        MaTV = maTV;
        AvataTV = avataTV;
        HoTen = hoTen;
        MatKhau = matKhau;
        this.SDT = SDT;
        Email = email;
        this.DChi = DChi;
        this.loai = loai;
    }


    public ThanhVien(int id, String maTV,  String hoTen, String matKhau, int SDT, String email, String DChi, String loai) {
        this.id = id;
        MaTV = maTV;
        HoTen = hoTen;
        MatKhau = matKhau;
        this.SDT = SDT;
        Email = email;
        this.DChi = DChi;
        this.loai = loai;
    }

    // hien thi
    public ThanhVien(String maTV, String hoTen, int SDT, String email, String DChi) {
        MaTV = maTV;
        HoTen = hoTen;
        this.SDT = SDT;
        Email = email;
        this.DChi = DChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTV() {
        return MaTV;
    }

    public void setMaTV(String maTV) {
        MaTV = maTV;
    }

    public String getAvataTV() {
        return AvataTV;
    }

    public void setAvataTV(String avataTV) {
        AvataTV = avataTV;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
