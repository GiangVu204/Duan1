package giangvhph33056.fpoly.duan1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import giangvhph33056.fpoly.duan1.Model.SanPham;

public class sanphamchitiet extends AppCompatActivity {
    TextView txtten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanphamchitiet);
        txtten = findViewById(R.id.txtten);
        Intent intent = getIntent();
        if (intent != null){
            SanPham sanPham = intent.getParcelableExtra("sanphamct");
            if(sanPham != null){
                Log.d(TAG,sanPham.getTenSP());
                txtten.setText(""+sanPham.getTenSP());

            }
        }
    }
}