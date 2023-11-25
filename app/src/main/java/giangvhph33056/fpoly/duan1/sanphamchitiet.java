package giangvhph33056.fpoly.duan1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import giangvhph33056.fpoly.duan1.Model.SanPham;

public class sanphamchitiet extends AppCompatActivity {
    TextView txttensp_ct,txtmasp_ct,txtgiasp_ct,txtsoluongsp_ct,txtsize_ct,txttenth_ct,txttenlsp_ct;
    Button btnthemgh_ct,btnMuangay_ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanphamchitiet);
        // ánh xạ
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
        btnMuangay_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
            // nhận dữ liệu được chuyền
        Intent intent = getIntent();
        if (intent != null){
            SanPham sanPham = intent.getParcelableExtra("sanphamct");
            if(sanPham != null){
                txttensp_ct.setText(sanPham.getTenSP());
                txtmasp_ct.setText(String.valueOf(sanPham.getMaSP()));
                txtgiasp_ct.setText(String.valueOf(sanPham.getGia()));
                txtsoluongsp_ct.setText(String.valueOf(sanPham.getSoLuong()));
                txtsize_ct.setText("Size" +sanPham.getSize());
                txttenth_ct.setText(sanPham.getTenthuonghieu());
                txttenlsp_ct.setText(sanPham.getTenlsp());

            }
        }
    }

}