package giangvhph33056.fpoly.duan1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_LoaiSanPham;
import giangvhph33056.fpoly.duan1.DAO.LoaiSanPhamDAO;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;
import giangvhph33056.fpoly.duan1.R;
public class Fragment_loai_san_pham extends Fragment {

    RecyclerView rcv_loaisanpham;
    FloatingActionButton fltadd;

    LoaiSanPhamDAO dao;
    public Fragment_loai_san_pham() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);

        rcv_loaisanpham = view.findViewById(R.id.rcv_loaisanpham);
        fltadd = view.findViewById(R.id.fltadd);
        dao = new LoaiSanPhamDAO(getContext());
        loadData();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAdd();

            }
        });
        return view;
    }

    private void dialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Tạo giao diện của Dialog từ layout XML
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogthemlsp, null);
        EditText tenlsp = dialogView.findViewById(R.id.tenlsp_add);
        // Thiết lập giao diện cho Dialog
        builder.setView(dialogView)
                .setTitle("Thêm loại sản phẩm")
                // Cấu hình các nút hoặc hành động trong Dialog nếu cần
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng chọn "Đồng ý"
                        String tslp = tenlsp.getText().toString();
                        if (tslp.isEmpty()){
                            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean check = dao.them(tslp);
                            if(check){
                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                loadData();
                            }
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng chọn "Hủy"
                        dialog.cancel();
                    }
                });

        // Hiển thị Dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_loaisanpham.setLayoutManager(layoutManager);
        ArrayList<LoaiSanPham> list = dao.getDSLoaiSanPham();
        Adapter_LoaiSanPham adapter = new Adapter_LoaiSanPham(getContext(),list);
        rcv_loaisanpham.setAdapter(adapter);
    }
}