package giangvhph33056.fpoly.duan1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import giangvhph33056.fpoly.duan1.DAO.GioHangDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.GioHang;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.Viewmd.SharedViewModel;

public class sanphamchitiet extends AppCompatActivity{
    TextView txttensp_ct,txtmasp_ct,txtgiasp_ct,txtsoluongsp_ct,txtsize_ct,txttenth_ct,txttenlsp_ct;
    Button btnthemgh_ct,btnMuangay_ct;
    EditText edt_soLuong_hd;
    Spinner spn_sanpham_hd,spn_thanhVien_hd;
    //HoaDonDAO hddao;
    SanPhamDAO spdao;
    GioHangDAO ghdao;
    private SharedViewModel sharedViewModel;

    private ArrayList<SanPham> listSanPham = new ArrayList<>();
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
        //hddao = new HoaDonDAO(this);
        spdao = new SanPhamDAO(this);
        ghdao = new GioHangDAO(this);
        listSanPham = spdao.selectAllSanPham();
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sanphamchitiet.this, Layout.class);
                startActivity(intent);
                finish();
            }
        });
//        btnMuangay_ct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialogadd();
//            }
//        });


        btnthemgh_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham selectedSanPham = intent.getParcelableExtra("sanphamct");
                if (selectedSanPham != null) {
                    addToCart(selectedSanPham);
                }
            }

        });
        // nhận dữ liệu được chuyền


    }
    private void addToCart(SanPham sp) {
        int masp = sp.getMaSP();
        int slSanPham = getSoLuongSp(masp);
        int mand = getSharedPreferences("DANGNHAPTV", MODE_PRIVATE).getInt("id", 0);
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        if (!sharedViewModel.isProductInCart(masp)) {
            if (slSanPham > 0) {
                // Nếu số lượng sản phẩm > 0, thêm sản phẩm vào giỏ hàng với số lượng là 1
                sharedViewModel.setMasp(masp);
                sharedViewModel.setAddToCartClicked(true);
                sharedViewModel.addProductToCart(masp);
                sharedViewModel.setQuantityToAdd(1);
                ghdao.insertGioHang(new GioHang(masp, mand, 1));
                Snackbar.make(getWindow().getDecorView().getRootView(), "Đã thêm vào giỏ hàng", Snackbar.LENGTH_SHORT).show();
            } else {
                // Nếu số lượng sản phẩm <= 0, thông báo người dùng
                Toast.makeText(this, "Sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Nếu sản phẩm đã có trong giỏ hàng
            GioHang gioHang = ghdao.getGioHangByMasp(masp, mand);
            if (gioHang != null) {
                if (slSanPham > 0 && gioHang.getSoLuongMua() < slSanPham) {
                    // Nếu có số lượng sản phẩm > 0 và số lượng trong giỏ hàng chưa đạt giới hạn
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    ghdao.updateGioHang(gioHang);
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    // Nếu số lượng trong giỏ hàng đã đạt giới hạn hoặc số lượng sản phẩm <= 0, thông báo người dùng
                    Toast.makeText(this, "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private int getSoLuongSp(int maSanPham) {
        for (SanPham s : listSanPham) {
            if (s.getMaSP() == maSanPham) {
                return s.getSoLuong();
            }
        }
        return 0;
    }
//    private void dialogadd (){
//        BottomSheetDialog dialog = new BottomSheetDialog(this);
//        View view = getLayoutInflater().inflate(R.layout.item_mua_ngay, null);
//        dialog.setContentView(view);
//        dialog.show();
//        edt_soLuong_hd = view.findViewById(R.id.edt_soLuong_hd);
//
//        spn_thanhVien_hd = view.findViewById(R.id.spn_thanhVien_hd);
//        spn_sanpham_hd = view.findViewById(R.id.spn_sanpham_hd);
//        Button SP_add = view.findViewById(R.id.SP_hd);
//        Button SP_cancel = view.findViewById(R.id.SP_Cancel_hd);
//
//        getDatathanhVien(spn_thanhVien_hd);
//        getDatasanpham(spn_sanpham_hd);
//
//        SP_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //lay ma Kich Thuoc
//                HashMap<String , String> hsKT = (HashMap<String, String>) spn_sanpham_hd.getSelectedItem();
//                int MaSP= Integer.parseInt(hsKT.get("MaSP"));
//                HashMap<String , String> hsLSP = (HashMap<String, String>) spn_thanhVien_hd.getSelectedItem();
//                int MaTV= Integer.parseInt(hsLSP.get("id"));
//                int soluong = Integer.parseInt(edt_soLuong_hd.getText().toString());
//                themsanpham(MaSP,soluong,MaTV);
//                dialog.dismiss();
//            }
//        });
//    }
//
//    private void getDatathanhVien(Spinner spn_thanhVien_hd) {
//        ThanhVienDAO DAO = new ThanhVienDAO(this);
//        ArrayList<ThanhVien> list = DAO.selectAllthanhVien();
//        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
//        for ( ThanhVien s : list) {
//            HashMap<String , String> hs = new HashMap<>();
//            hs.put("id" , String.valueOf(s.getId()));
//            hs.put("MaTV" , s.getMaTV());
//            hs.put("HoTen" , s.getHoTen());
//            listHM.add(hs);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(this,listHM,android.R.layout.simple_list_item_1,new String[]{"HoTen"}, new int[]{android.R.id.text1});
//        spn_thanhVien_hd.setAdapter(adapter);
//    }
//    private void getDatasanpham(Spinner spn_sanpham_hd) {
//        SanPhamDAO DAO = new SanPhamDAO(this);
//        ArrayList<SanPham> list = DAO.selectAllSanPham();
//        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
//        for ( SanPham s : list) {
//            HashMap<String , String> hs = new HashMap<>();
//            hs.put("MaSP" , String.valueOf(s.getMaSP()));
//            hs.put("TenSP", s.getTenSP());
//            listHM.add(hs);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(this,listHM,android.R.layout.simple_list_item_1,new String[]{"TenSP"}, new int[]{android.R.id.text1});
//        spn_sanpham_hd.setAdapter(adapter);
//    }
//
//    private void themsanpham( int  MaSP, int soluong, int MaTV){
//        long kiemtra = hddao.thucHienMuaHang(MaSP, soluong, MaTV);
//        if (kiemtra == 1){
//            Toast.makeText(this, "mua thành  thành công!", Toast.LENGTH_SHORT).show();
//        }else if (kiemtra == -1){
//            Toast.makeText(this, "mua thất bại", Toast.LENGTH_SHORT).show();}
//
//
//    }
//
//    private void themSanPhamVaoGioHang(String AvataSP, String tenSP, int soLuong, double gia) {
//        Log.d("GioHang","AvataSP: " + AvataSP + "TenSP: " + tenSP + ", SoLuong: " + soLuong + ", Gia: " + gia);
//
//        SanPhamDAO gioHangDAO = new SanPhamDAO(this);
//        long kiemTra = gioHangDAO.themSanPhamVaoGioHang(tenSP, soLuong, gia, AvataSP);
//
//
//        if (kiemTra != -1) {
//            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
//        }
//    }

}