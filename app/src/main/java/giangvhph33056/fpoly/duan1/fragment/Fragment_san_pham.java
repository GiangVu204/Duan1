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

import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThanhVien;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;
public class Fragment_san_pham extends Fragment {
    SanPhamDAO spDAO;
    Adapter_SanPham adaptersp;
    RecyclerView rcv_sanpham;
    private ArrayList<SanPham> list = new ArrayList<>();
    FloatingActionButton fltadd;
    public Fragment_san_pham() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_san_pham, container, false);
        rcv_sanpham = view.findViewById(R.id.rcv_sanpham);
        fltadd = view.findViewById(R.id.fltadd);
        spDAO= new SanPhamDAO(getContext());
        list = spDAO.selectAllSanPham();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_sanpham.setLayoutManager(linearLayoutManager);
        rcv_sanpham.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adaptersp = new Adapter_SanPham(getContext(), list);
        rcv_sanpham.setAdapter(adaptersp);
        return view;
    }
}