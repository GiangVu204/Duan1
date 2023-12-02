package giangvhph33056.fpoly.duan1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_GioHang;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.DAO.HoaDonDAO;
import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.DAO.LoaiSanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.HoaDon;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;

public class sanphamchitiet extends AppCompatActivity{
    TextView txttensp_ct,txtmasp_ct,txtgiasp_ct,txtsoluongsp_ct,txtsize_ct,txttenth_ct,txttenlsp_ct;
    Button btnthemgh_ct,btnMuangay_ct;
    EditText edt_soLuong_hd;
    Spinner spn_sanpham_hd,spn_thanhVien_hd;
    Button SP_hd,SP_Cancel_hd;
    HoaDonDAO hddao;

    private ArrayList<SanPham> list;
    ImageView back, ImaSP;
     // Biến thành viên để lưu đường dẫn ảnh
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanphamchitiet);
        // ánh xạ
        back = findViewById(R.id.back);
        ImaSP = findViewById(R.id.ImaSP);
        txttensp_ct = findViewById(R.id.txttensp_ct);
        txtmasp_ct = findViewById(R.id.txtmasp_ct);
        txtgiasp_ct = findViewById(R.id.txtgiasp_ct);
        txtsoluongsp_ct = findViewById(R.id.txtsoluongsp_ct);
        txtsize_ct = findViewById(R.id.txtsize_ct);
        txttenth_ct = findViewById(R.id.txttenth_ct);
        txttenlsp_ct = findViewById(R.id.txttenlsp_ct);
        ///
        btnthemgh_ct = findViewById(R.id.btnthemgh_ct);
        btnMuangay_ct = findViewById(R.id.btnMuangay_ct);
        hddao = new HoaDonDAO(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sanphamchitiet.this, Layout.class);
                startActivity(intent);
                finish();
            }
        });
        btnMuangay_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogadd();
            }
        });
        btnthemgh_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Truyền đường dẫn ảnh qua Intent
                SanPham sp = new SanPham();
                // Lay thong tin san pham
                String tenSP = txttensp_ct.getText().toString();
                String avata = "";
                int soLuong = Integer.parseInt(txtsoluongsp_ct.getText().toString());
                double gia = Double.parseDouble(txtgiasp_ct.getText().toString());
//                Picasso.get().load(sp.getAvataSP()).into(ImaSP);

                // Thêm sản phẩm vào giỏ hàng
                themSanPhamVaoGioHang(avata, tenSP, soLuong, gia);
                }


        });
        // nhận dữ liệu được chuyền
        Intent intent = getIntent();
        if (intent != null) {
            SanPham sanPham = intent.getParcelableExtra("sanphamct");
            String anhSP = intent.getStringExtra("anhsp");
            if (sanPham != null) {
                // Load ảnh vào ImageView
                Picasso.get().load(anhSP).into(ImaSP);
                txttensp_ct.setText(sanPham.getTenSP());
                txtmasp_ct.setText(String.valueOf(sanPham.getMaSP()));
                txtgiasp_ct.setText(String.valueOf(sanPham.getGia()));
                txtsoluongsp_ct.setText(String.valueOf(sanPham.getSoLuong()));
                txtsize_ct.setText("Size" + sanPham.getSize());
                txttenth_ct.setText(sanPham.getTenthuonghieu());
                txttenlsp_ct.setText(sanPham.getTenlsp());


            }
        }


    }
    private void dialogadd (){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.item_mua_ngay, null);
        dialog.setContentView(view);
        dialog.show();
        edt_soLuong_hd = view.findViewById(R.id.edt_soLuong_hd);

        spn_thanhVien_hd = view.findViewById(R.id.spn_thanhVien_hd);
        spn_sanpham_hd = view.findViewById(R.id.spn_sanpham_hd);
        Button SP_add = view.findViewById(R.id.SP_hd);
        Button SP_cancel = view.findViewById(R.id.SP_Cancel_hd);

        getDatathanhVien(spn_thanhVien_hd);
        getDatasanpham(spn_sanpham_hd);

        SP_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lay ma Kich Thuoc
                HashMap<String , String> hsKT = (HashMap<String, String>) spn_sanpham_hd.getSelectedItem();
                int MaSP= Integer.parseInt(hsKT.get("MaSP"));
                HashMap<String , String> hsLSP = (HashMap<String, String>) spn_thanhVien_hd.getSelectedItem();
                int MaTV= Integer.parseInt(hsLSP.get("id"));
                int soluong = Integer.parseInt(edt_soLuong_hd.getText().toString());
                themsanpham(MaSP,soluong,MaTV);
                dialog.dismiss();
            }
        });
    }

    private void getDatathanhVien(Spinner spn_thanhVien_hd) {
        ThanhVienDAO DAO = new ThanhVienDAO(this);
        ArrayList<ThanhVien> list = DAO.selectAllthanhVien();
        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
        for ( ThanhVien s : list) {
            HashMap<String , String> hs = new HashMap<>();
            hs.put("id" , String.valueOf(s.getId()));
            hs.put("MaTV" , s.getMaTV());
            hs.put("HoTen" , s.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listHM,android.R.layout.simple_list_item_1,new String[]{"HoTen"}, new int[]{android.R.id.text1});
        spn_thanhVien_hd.setAdapter(adapter);
    }
    private void getDatasanpham(Spinner spn_sanpham_hd) {
        SanPhamDAO DAO = new SanPhamDAO(this);
        ArrayList<SanPham> list = DAO.selectAllSanPham();
        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
        for ( SanPham s : list) {
            HashMap<String , String> hs = new HashMap<>();
            hs.put("MaSP" , String.valueOf(s.getMaSP()));
            hs.put("TenSP", s.getTenSP());
            listHM.add(hs);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listHM,android.R.layout.simple_list_item_1,new String[]{"TenSP"}, new int[]{android.R.id.text1});
        spn_sanpham_hd.setAdapter(adapter);
    }

    private void themsanpham( int  MaSP, int soluong, int MaTV){
        long kiemtra = hddao.thucHienMuaHang(MaSP, soluong, MaTV);
        if (kiemtra == 1){
            Toast.makeText(this, "mua thành  thành công!", Toast.LENGTH_SHORT).show();
        }else if (kiemtra == -1){
            Toast.makeText(this, "mua thất bại", Toast.LENGTH_SHORT).show();}


    }

    private void themSanPhamVaoGioHang(String AvataSP, String tenSP, int soLuong, double gia) {
        Log.d("GioHang","AvataSP: " + AvataSP + "TenSP: " + tenSP + ", SoLuong: " + soLuong + ", Gia: " + gia);

        SanPhamDAO gioHangDAO = new SanPhamDAO(this);
        long kiemTra = gioHangDAO.themSanPhamVaoGioHang(tenSP, soLuong, gia, AvataSP);

        if (kiemTra != -1) {
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }

}