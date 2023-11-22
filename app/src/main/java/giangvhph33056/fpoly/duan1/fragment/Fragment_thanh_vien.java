package giangvhph33056.fpoly.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_KichThuoc;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThanhVien;
import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;
public class Fragment_thanh_vien extends Fragment {
    ThanhVienDAO tvDAO;
    Adapter_ThanhVien adaptertv;
    RecyclerView rcv_thanhvien;
    FloatingActionButton fltadd;

    private ArrayList<ThanhVien> list = new ArrayList<>();
    public Fragment_thanh_vien() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        rcv_thanhvien = view.findViewById(R.id.rcv_thanhvien);
        fltadd = view.findViewById(R.id.fltadd);
        tvDAO= new ThanhVienDAO(getContext());
        list = tvDAO.selectAllthanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_thanhvien.setLayoutManager(linearLayoutManager);
        rcv_thanhvien.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adaptertv = new Adapter_ThanhVien(getContext(), list);
        rcv_thanhvien.setAdapter(adaptertv);

        return view;
    }
}