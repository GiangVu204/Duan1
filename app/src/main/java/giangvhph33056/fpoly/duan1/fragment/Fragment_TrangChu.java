package giangvhph33056.fpoly.duan1.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_trangchu;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.Gio_Hang_Activity;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.R;
import giangvhph33056.fpoly.duan1.TimKiem;
import giangvhph33056.fpoly.duan1.sanphamchitiet;

public class Fragment_TrangChu extends Fragment {
    TextView txttennguoidung_tt;
    ImageView ImgGiohang;
    RecyclerView rcvsanpham_tt;
    SanPhamDAO spDAO;
    Adapter_trangchu adaptersptt;
    private ArrayList<SanPham> list = new ArrayList<>();
    public Fragment_TrangChu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__trang_chu, container, false);
        txttennguoidung_tt = view.findViewById(R.id.txttennguoidung_tt);
        ImgGiohang = view.findViewById(R.id.ImgGiohang);
        EditText edttimkiem = view.findViewById(R.id.edttimkiem);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DANGNHAPTV", Context.MODE_PRIVATE);
        String loai = sharedPreferences.getString("HoTen","");
        txttennguoidung_tt.setText(loai);
        ///

        rcvsanpham_tt =view.findViewById(R.id.rcvsanpham_tt);
        spDAO= new SanPhamDAO(getContext());
        list = spDAO.selectAllSanPham();
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rcvsanpham_tt.setLayoutManager(layoutManager);
        adaptersptt = new Adapter_trangchu(getContext(), list);
        rcvsanpham_tt.setAdapter(adaptersptt);

        ImgGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Gio_Hang_Activity.class);
                startActivity(intent);
            }
        });
        adaptersptt.setClick(new Adapter_SanPham.click() {
            @Override
            public void click(int pos) {
                SanPham hienthi = list.get(pos);
                Intent intent = new Intent(getContext(), sanphamchitiet.class);
                intent.putExtra("sanphamct",hienthi);
                startActivity(intent);
            }
        });
        edttimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),  TimKiem.class);
                startActivity(intent);
            }
        });
        return view;
    }
}