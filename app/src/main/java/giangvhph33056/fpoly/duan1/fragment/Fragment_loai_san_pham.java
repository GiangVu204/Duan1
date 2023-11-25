package giangvhph33056.fpoly.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return view;
    }

    public void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_loaisanpham.setLayoutManager(layoutManager);
        ArrayList<LoaiSanPham> list = dao.getDSLoaiSanPham();
        Adapter_LoaiSanPham adapter = new Adapter_LoaiSanPham(getContext(),list);
        rcv_loaisanpham.setAdapter(adapter);
    }
}