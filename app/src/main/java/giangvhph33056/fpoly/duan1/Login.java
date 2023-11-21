package giangvhph33056.fpoly.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;

public class Login extends AppCompatActivity {
    TextInputEditText edtUser,edtPass;
    AppCompatButton btnLOGIN;
    ThanhVienDAO tvdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLOGIN = findViewById(R.id.btnLOGIN);
        CheckBox chkNho = findViewById(R.id.chkNho);
        // hiên thi tài khoản mật khẩu đã nhớ lêm edt
        SharedPreferences sharedPreferences = getSharedPreferences("DANGNHAPTV", MODE_PRIVATE);
        String saveduser = sharedPreferences.getString("MaTV", "");
        String savedPassword = sharedPreferences.getString("MatKhau", "");
        if (!savedPassword.isEmpty()) {
            edtUser.setText(saveduser);
            edtPass.setText(savedPassword);
        }
        //xử lí nút đăng nhập
        tvdao = new ThanhVienDAO(this);
        btnLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(tvdao.checklogin(user,pass)){
                    /// nhớ tài khoan mật khẩu
                    if (chkNho.isChecked()==true) {
                        SharedPreferences sharedPreferences = getSharedPreferences("DANGNHAPTV", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("MaTV", user);
                        editor.putString("MatKhau", pass); // Lưu mật khẩu vào SharedPreferences
                        editor.apply();

                    }
                    Intent intent = new Intent(Login.this,Layout.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Đăng nhập Thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Dăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}