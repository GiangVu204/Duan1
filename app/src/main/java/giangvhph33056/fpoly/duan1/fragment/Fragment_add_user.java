package giangvhph33056.fpoly.duan1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.DangKi;
import giangvhph33056.fpoly.duan1.Login;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;

public class Fragment_add_user extends Fragment {
    EditText edtAvata_tnd, edtUser_tnd,edtPass_tnd,edthoten_tnd,edtEmail_tnd,edtsdt_tnd;
    Button btnTHEM;
    ThanhVienDAO tvDAO;
    public Fragment_add_user() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_user, container, false);
        edtAvata_tnd = view.findViewById(R.id.edtAvata_tnd);
        edtUser_tnd = view.findViewById(R.id.edtUser_tnd);
        edtPass_tnd = view.findViewById(R.id.edtPass_tnd);
        edthoten_tnd = view.findViewById(R.id.edthoten_tnd);
        edtEmail_tnd = view.findViewById(R.id.edtEmail_tnd);
        edtsdt_tnd = view.findViewById(R.id.edtsdt_tnd);
            btnTHEM =view.findViewById(R.id.btnTHEM);
            tvDAO = new ThanhVienDAO(getContext());



        btnTHEM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                ThanhVien kt = new ThanhVien();
//                int id = kt.getId();
                    String avatatnd =edtAvata_tnd.getText().toString();
                    String ma = edtUser_tnd.getText().toString();
                    String pass = edtPass_tnd.getText().toString();
                    String hten = edthoten_tnd.getText().toString();
                    String email = edtEmail_tnd.getText().toString();
                    String sdt = edtsdt_tnd.getText().toString();
                    String dc = "Hà Nội";
                    String loai ="Nhân Viên";
                    ThanhVien kt = new ThanhVien(ma,avatatnd,hten,pass,sdt,email,dc,loai);
                    if(tvDAO.insert(kt)){
                        Toast.makeText(getContext(), "ĐĂNG KÝ THÀNH CÔNG", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getContext(), "ĐĂNG KÝ THẤT BẠI", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        return view;
    }
}