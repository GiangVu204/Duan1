package giangvhph33056.fpoly.duan1.Model;

public class KichThuoc {
    private int MaKT;
    private int Size;
    private int SoLuong;

    public KichThuoc() {
    }

    public KichThuoc(int maKT, int size, int soLuong) {
        MaKT = maKT;
        Size = size;
        SoLuong = soLuong;
    }

    public int getMaKT() {
        return MaKT;
    }

    public void setMaKT(int maKT) {
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
