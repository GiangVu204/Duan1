package giangvhph33056.fpoly.duan1.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_Nap_Tien;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThanhVien;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThuongHieu;
import giangvhph33056.fpoly.duan1.DAO.NapTienDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.NapTien;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;
import giangvhph33056.fpoly.duan1.R;

public class Fragment_NapTien extends Fragment {



    public Fragment_NapTien() {
        // Required empty public constructor
    }
    NapTienDAO ntdao;
    Button fltadd;
    ImageView imgNT;

    private Uri selectedImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__nap_tien, container, false);
        fltadd = view.findViewById(R.id.fltadd);
        ntdao = new NapTienDAO(getContext());
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogAddTH();
            }
        });
        return view;
    }
    public void DiaLogAddTH() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_naptien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        imgNT = view.findViewById(R.id.imgNT);
        TextInputEditText ed_sotien = view.findViewById(R.id.ed_sotien);
        Button AddTH = view.findViewById(R.id.TH_Add);
        Button CancelTH = view.findViewById(R.id.TH_Canceladd);

        imgNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi phương thức để chọn ảnh từ thiết bị
                pickImage();
            }
        });

        AddTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("DANGNHAPTV", Context.MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", 0);
                int sotin = Integer.parseInt(ed_sotien.getText().toString());
                String TenNXN = " Chưa xác nhận";
                LocalDate currentDate = LocalDate.now();
                int TrangThai = 0;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngayHienTai = currentDate.format(formatter);

                // Check if an image is selected
                if (selectedImageUri != null) {
                    String AnhNT = selectedImageUri.toString();

                NapTien nt = new NapTien(AnhNT, sotin, ngayHienTai, TenNXN, TrangThai, id);
                if (ntdao.insert(nt)) {
                    Toast.makeText(getActivity(), "Gui phieu nap thành công!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getActivity(), "Gui phieu nap thất bại!!!", Toast.LENGTH_SHORT).show();
                }
                } else {
                    // Handle the case when no image is selected
                    Toast.makeText(getContext(), "Vui lòng chọn ảnh thương hiệu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CancelTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ed_addAvataTH.setText("");
//                ed_SDT.setText("");
//                ed_TenTH.setText("");
            }
        });
    }

    // Phương thức chọn ảnh từ thiết bị
    private void pickImage() {
        // Mở Intent để chọn ảnh từ bộ nhớ thiết bị
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Lấy đường dẫn của ảnh được chọn
            selectedImageUri = data.getData();
            // Hiển thị ảnh trong ImageView (hoặc thực hiện các xử lý khác tùy ý)
            imgNT.setImageURI(selectedImageUri);
            // Gọi phương thức để cập nhật ảnh trong Adapter_ThuongHieu
//            adapter.setCurrentImageUri(selectedImageUri);
        }
    }

    }



