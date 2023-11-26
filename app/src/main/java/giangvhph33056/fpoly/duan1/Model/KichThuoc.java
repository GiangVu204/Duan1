package giangvhph33056.fpoly.duan1.Model;

public class KichThuoc {

    private int id;
    private String MaKT;
    private int Size;
    private int SoLuong;

    public KichThuoc(String maKT, int size, int soLuong) {
        MaKT = maKT;
        Size = size;
        SoLuong = soLuong;
    }

    public KichThuoc() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKT() {
        return MaKT;
    }

    public void setMaKT(String maKT) {
        MaKT = maKT;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}