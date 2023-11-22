package giangvhph33056.fpoly.duan1.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
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

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThuongHieu;
import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;
import giangvhph33056.fpoly.duan1.R;

public class Fragment_thuong_hieu extends Fragment {

    FloatingActionButton fltAdd;
    RecyclerView rcvTH;
    ThuongHieuDAO THdao;

    ImageView ImgAnhh;
    Uri selectedImageUri;
    public Fragment_thuong_hieu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuong_hieu, container, false);

        rcvTH = view.findViewById(R.id.rcvThuonghieu);
        fltAdd = view.findViewById(R.id.fltadd);
        THdao = new ThuongHieuDAO(getContext());
        loadData();
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogAddTH();
            }
        });
        return view;
    }

    public void DiaLogAddTH(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_thuonghieu,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_SDT = view.findViewById(R.id.in_addSDT);
        TextInputEditText ed_SDT = view.findViewById(R.id.ed_addSDT);
        TextInputLayout in_TenTH = view.findViewById(R.id.in_addTenTH);
        TextInputEditText ed_TenTH = view.findViewById(R.id.ed_addTenTH);
        ImgAnhh = view.findViewById(R.id.ImgAnhh);
        Button AddTH = view.findViewById(R.id.TH_Add);
        Button CancelTH = view.findViewById(R.id.TH_Canceladd);

        ImgAnhh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để chọn ảnh từ thiết bị
                pickImage();
            }
        });

        ed_SDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_SDT.setError("Vui lòng không để trống Số điện thoại");
                }else {
                    in_SDT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_TenTH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_TenTH.setError("Vui lòng không để trống Tên thương hiệu");
                }else {
                    in_TenTH.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        AddTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SDT = ed_SDT.getText().toString();
                String TenTH = ed_TenTH.getText().toString();

                // Check if an image is selected
                if (selectedImageUri != null) {
                    String Anhh = selectedImageUri.toString();

                    if (SDT.isEmpty() || TenTH.isEmpty()) {
                        // Display error messages if necessary fields are empty
                        if (SDT.isEmpty()) {
                            in_SDT.setError("Vui lòng không để trống Số điện thoại!");
                        } else {
                            in_SDT.setError(null);
                        }
                        if (TenTH.isEmpty()) {
                            in_TenTH.setError("Vui lòng không để trống tên thương hiệu!");
                        } else {
                            in_TenTH.setError(null);
                        }
                    } else {
                        // Insert the data into the database
                        if (THdao.insert(SDT, Anhh, TenTH)) {
                            loadData();
                            Toast.makeText(getContext(), "Thêm thương hiệu thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm thương hiệu thất bại!!!", Toast.LENGTH_SHORT).show();
                        }
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
                ed_SDT.setText("");
                ed_TenTH.setText("");
            }
        });

    }

    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvTH.setLayoutManager(layoutManager);
        ArrayList<ThuongHieu> list = THdao.getDSThuongHieu();
        Adapter_ThuongHieu adapter = new Adapter_ThuongHieu(getContext(),list);
        rcvTH.setAdapter(adapter);
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
            ImgAnhh.setImageURI(selectedImageUri);
        }
    }
}