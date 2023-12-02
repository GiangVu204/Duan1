package giangvhph33056.fpoly.duan1;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_GioHang;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_KichThuoc;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.Model.GioHang;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.SanPham;

public class Gio_Hang_Activity extends AppCompatActivity {
    TextView giohangtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recycleView;
    Button btnmuahang;
    //    Adapter_GioHang adapter;
    SanPhamDAO spDAO;
    private Adapter_GioHang adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        spDAO = new SanPhamDAO(this);
        recycleView = findViewById(R.id.recycleviewgiohang);
//        giohangtrong = findViewById(R.id.txtgiohangtrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.toolbar);
        btnmuahang = findViewById(R.id.btnmuahang);

        loadData();
    }

    public void loadData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);
        ArrayList<SanPham> list = spDAO.selectAllGioHang();
        Adapter_GioHang adapter = new Adapter_GioHang(this, list);
        recycleView.setAdapter(adapter);
    }
}
