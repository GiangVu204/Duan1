package giangvhph33056.fpoly.duan1.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_KichThuoc;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_LoaiSanPham;
import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;
import giangvhph33056.fpoly.duan1.R;

public class Fragment_kich_thuoc extends Fragment {
    KichThuocDAO ktDAO;
    Adapter_KichThuoc adapterkt;
    RecyclerView rcv_kichthuoc;
    FloatingActionButton fltadd;

    private ArrayList<KichThuoc> list = new ArrayList<>();


    public Fragment_kich_thuoc() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kich_thuoc, container, false);
        // ánh xạ
        rcv_kichthuoc = view.findViewById(R.id.rcv_kichthuoc);
        fltadd = view.findViewById(R.id.fltadd);
        ktDAO = new KichThuocDAO(getContext());
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
        View dialogView = inflater.inflate(R.layout.item_kich_thuoc_add, null);
        EditText edtsize_kt = dialogView.findViewById(R.id.edtsize_kt);
        EditText edtSoluong_kt = dialogView.findViewById(R.id.edtSoluong_kt);
        // Thiết lập giao diện cho Dialog
        builder.setView(dialogView)
                .setTitle("Thêm kích thước")
                // Cấu hình các nút hoặc hành động trong Dialog nếu cần
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xử lý khi người dùng chọn "Đồng ý"
                        String size = edtsize_kt.getText().toString();
                        String Soluong = edtSoluong_kt.getText().toString();
                        if (size.isEmpty() || Soluong.isEmpty()){
                            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean check = ktDAO.insert(size, Soluong);
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
        rcv_kichthuoc.setLayoutManager(layoutManager);
        ArrayList<KichThuoc> list = ktDAO.selectAllKichThuoc();
        Adapter_KichThuoc adapter = new Adapter_KichThuoc(getContext(),list);
        rcv_kichthuoc.setAdapter(adapter);
    }
}