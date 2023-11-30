package giangvhph33056.fpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class DangKi extends AppCompatActivity {
    EditText edtAvata_dk, edtUser_dk,edtPass_dk,edthoten_dk,edtEmail_dk,edtsdt_dk;
    Button btnSIGN;
    ThanhVienDAO tvDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtAvata_dk = findViewById(R.id.edtAvata_dk);
        edtUser_dk = findViewById(R.id.edtUser_dk);
        edtPass_dk = findViewById(R.id.edtPass_dk);
        edthoten_dk = findViewById(R.id.edthoten_dk);
        edtEmail_dk = findViewById(R.id.edtEmail_dk);
        edtsdt_dk = findViewById(R.id.edtsdt_dk);
        btnSIGN = findViewById(R.id.btnSIGN);
        tvDAO = new ThanhVienDAO(this);



        btnSIGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ThanhVien kt = new ThanhVien();
//                int id = kt.getId();
                String avatadk = edtAvata_dk.getText().toString();
                String ma = edtUser_dk.getText().toString();
                String pass = edtPass_dk.getText().toString();
                String hten = edthoten_dk.getText().toString();
                String email = edtEmail_dk.getText().toString();
                int sdt = Integer.parseInt(edtsdt_dk.getText().toString());
                String dc = "Hà Nội";
                String loai ="Khách Hàng";
                ThanhVien kt = new ThanhVien(ma,avatadk,hten,pass,sdt,email,dc,loai);
                if(tvDAO.insert(kt)){
                    Toast.makeText(DangKi.this, "DĂNG KÝ THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKi.this,  Login.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(DangKi.this, "DĂNG KÝ THẤT BẠI", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}