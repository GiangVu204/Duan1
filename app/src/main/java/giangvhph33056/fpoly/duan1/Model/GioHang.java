package giangvhph33056.fpoly.duan1.Model;

public class GioHang {
    // Thêm các thuộc tính mới
    private String AvataGH;
    private String tenSanPham;
    private int giaSanPham;
    private int soLuong;

    public GioHang() {
    }

    public GioHang(String avataGH, String tenSanPham, int giaSanPham, int soLuong) {
        AvataGH = avataGH;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
    }

    public GioHang(String tenSanPham, int giaSanPham, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
    }


    public String getAvataGH() {
        return AvataGH;
    }

    public void setAvataGH(String avataGH) {
        AvataGH = avataGH;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
