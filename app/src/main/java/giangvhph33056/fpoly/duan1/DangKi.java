package giangvhph33056.fpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class DangKi extends AppCompatActivity {
    TextInputEditText edtAvata_dk, edtUser_dk,edtPass_dk,edthoten_dk,edtEmail_dk,edtsdt_dk;
    TextInputLayout in_Avata_dk;
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
                String sdt = edtsdt_dk.getText().toString();
                String dc = "Hà Nội";
                String loai ="Khách Hàng";
                // Kiểm tra các trường nhập liệu không được để trống
                if (ma.isEmpty() || pass.isEmpty() || hten.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
                    Toast.makeText(DangKi.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra số điện thoại có số 0 ở đầu, chỉ chứa chữ số và có từ 10 đến 12 ký tự
                if (!sdt.matches("0\\d*") || !sdt.matches("\\d{10,12}")) {
                    Toast.makeText(DangKi.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra định dạng email hợp lệ
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(DangKi.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tiến hành đăng ký thành viên

                ThanhVien kt = new ThanhVien(ma,avatadk,hten,pass,sdt,email,dc,loai);
                if(tvDAO.insert(kt)){
                    Toast.makeText(DangKi.this, "ĐĂNG KÝ THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangKi.this,  Login.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(DangKi.this, "ĐĂNG KÝ THẤT BẠI", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}