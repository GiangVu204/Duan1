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

import giangvhph33056.fpoly.duan1.Adapter.Adapter_HoaDon;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.DAO.HoaDonDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.Model.HoaDon;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.R;
public class Fragment_hoa_don extends Fragment {
    HoaDonDAO hdDAO;
    Adapter_HoaDon adapterhd;
    RecyclerView rcv_hoadon;
    private ArrayList<HoaDon> list = new ArrayList<>();
    FloatingActionButton fltadd;
    public Fragment_hoa_don() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        rcv_hoadon = view.findViewById(R.id.rcv_hoadon);
        fltadd = view.findViewById(R.id.fltadd);
        hdDAO= new HoaDonDAO(getContext());
        list = hdDAO.selectAllHoaDon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_hoadon.setLayoutManager(linearLayoutManager);
        rcv_hoadon.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapterhd = new Adapter_HoaDon(getContext(), list);
        rcv_hoadon.setAdapter(adapterhd);

        return view;
    }
}