package giangvhph33056.fpoly.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.fragment.Fragment_add_user;
import giangvhph33056.fpoly.duan1.fragment.Fragment_doanh_thu;
import giangvhph33056.fpoly.duan1.fragment.Fragment_doi_mk;
import giangvhph33056.fpoly.duan1.fragment.Fragment_hoa_don;
import giangvhph33056.fpoly.duan1.fragment.Fragment_kich_thuoc;
import giangvhph33056.fpoly.duan1.fragment.Fragment_loai_san_pham;
import giangvhph33056.fpoly.duan1.fragment.Fragment_san_pham;
import giangvhph33056.fpoly.duan1.fragment.Fragment_thanh_vien;
import giangvhph33056.fpoly.duan1.fragment.Fragment_thuong_hieu;

public class Layout extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    Context context = this;
    ThanhVienDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        View view = navigationView.getHeaderView(0);
        TextView txtloaitv = view.findViewById(R.id.txtloaitv_hd);
        TextView txtemailtv = view.findViewById(R.id.txtemailtv_hd);
        TextView txthotentv_hd = view.findViewById(R.id.txthotentv_hd);
        dao = new ThanhVienDAO(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        // set drawer toggle

        // Thêm mã để hiển thị Fragment_san_pham khi ứng dụng được khởi chạy
        Fragment_san_pham frgSP = new Fragment_san_pham();
        relaceFrg(frgSP);
        toolbar.setTitle("Quản lý sản phẩm");
if (savedInstanceState == null){
    relaceFrg(new Fragment_san_pham());
}
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.SanPham){
                    Fragment_san_pham frgPM = new Fragment_san_pham();
                    relaceFrg(frgPM);
                    toolbar.setTitle("Quản lý sản phẩm");
                } else if (item.getItemId() == R.id.LoaiSanPham) {
                    Fragment_loai_san_pham frgLS = new Fragment_loai_san_pham();
                    relaceFrg(frgLS);
                    toolbar.setTitle("Quản lý loại sản phẩm");
                } else if (item.getItemId() == R.id.KichThuoc) {
                    Fragment_kich_thuoc frgTV = new Fragment_kich_thuoc();
                    relaceFrg(frgTV);
                    toolbar.setTitle("Quản lý kích thước");
                }else if (item.getItemId() == R.id.ThuongHieu){
                    Fragment_thuong_hieu frgT = new Fragment_thuong_hieu();
                    relaceFrg(frgT);
                    toolbar.setTitle("Quản lý thương hiệu");
                }else if (item.getItemId() == R.id.ThanhVien){
                    Fragment_thanh_vien frgT = new Fragment_thanh_vien();
                    relaceFrg(frgT);
                    toolbar.setTitle("Quản lý thành viên");
                }else if (item.getItemId() == R.id.HoaDon){
                    Fragment_hoa_don frgT = new Fragment_hoa_don();
                    relaceFrg(frgT);
                    toolbar.setTitle("Quản lý hóa đon");
                }else if (item.getItemId() == R.id.menuDT){
                    Fragment_doanh_thu frgDT = new Fragment_doanh_thu();
                    relaceFrg(frgDT);
                    toolbar.setTitle("Quản lý doanh thu");
                } else if (item.getItemId() == R.id.menuTND) {
                    Fragment_add_user frgTND = new Fragment_add_user();
                    relaceFrg(frgTND);
                    toolbar.setTitle("Thêm người dùng");
                } else if (item.getItemId() == R.id.menuDMK) {
                    Fragment_doi_mk frgDMK = new Fragment_doi_mk();
                    relaceFrg(frgDMK);
                    toolbar.setTitle("Đổi mật khẩu");
                } else if (item.getItemId() == R.id.menuDX) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chắn muốn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(Layout.this, Login.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy", null);
                    builder.create().show();
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("DANGNHAPTV",MODE_PRIVATE);
        String Loai = sharedPreferences.getString("Loai","");
        if(Loai.equalsIgnoreCase("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.menuTND).setVisible(true);
            menu.findItem(R.id.thongtin).setVisible(false);
            menu.findItem(R.id.lienhe).setVisible(false);
        }
        if(Loai.equalsIgnoreCase("Nhân Viên")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.menuTND).setVisible(false);
            menu.findItem(R.id.thongtin).setVisible(false);
            menu.findItem(R.id.lienhe).setVisible(false);

        }
        if(Loai.equalsIgnoreCase("Khách Hàng")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.LoaiSanPham).setVisible(false);
            menu.findItem(R.id.KichThuoc).setVisible(false);
            menu.findItem(R.id.ThanhVien).setVisible(false);
            menu.findItem(R.id.menuDT).setVisible(false);
            menu.findItem(R.id.menuDT).setVisible(false);
            menu.findItem(R.id.ThuongHieu).setVisible(false);
            menu.findItem(R.id.menuTND).setVisible(false);

        }
        String loai = sharedPreferences.getString("Loai","");
        txtloaitv.setText(loai);
        String email = sharedPreferences.getString("Email","");
        txtemailtv.setText(email);
        String Hoten = sharedPreferences.getString("HoTen","");
        txthotentv_hd.setText(Hoten);

    }
    public void relaceFrg(Fragment frg){
        FragmentManager fg = getSupportFragmentManager();
        fg.beginTransaction().replace(R.id.frameLayout,frg).commit();
    }
}