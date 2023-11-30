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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_GioHang;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class sanphamchitiet extends AppCompatActivity{
    TextView txttensp_ct,txtmasp_ct,txtgiasp_ct,txtsoluongsp_ct,txtsize_ct,txttenth_ct,txttenlsp_ct;
    Button btnthemgh_ct,btnMuangay_ct;

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnMuangay_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnthemgh_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Truyền đường dẫn ảnh qua Intent

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

//    @Override
//    public void click(int pos, Adapter_SanPham adapter) {
//        SanPham selectedSanPham = adapter.getSelectedItem(pos);
//
//        if (selectedSanPham != null) {
//            String avataSPUrl = selectedSanPham.getAvataSP();
//
//            Intent intent = new Intent(sanphamchitiet.this, sanphamchitiet.class);
//            intent.putExtra("sanphamct", selectedSanPham);
//            intent.putExtra("avataSPUrl", avataSPUrl);
//            startActivity(intent);
//        }
//    }
}