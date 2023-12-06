package giangvhph33056.fpoly.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.adapter_thanh_toan;
import giangvhph33056.fpoly.duan1.DAO.DonHangChiTietDao;
import giangvhph33056.fpoly.duan1.Model.DonHangChiTiet;
import giangvhph33056.fpoly.duan1.R;
import giangvhph33056.fpoly.duan1.databinding.FragmentConfilmThanhtoanBinding;


public class Fragment_confilm_thanhtoan extends Fragment {

    private FragmentConfilmThanhtoanBinding binding;
    private ArrayList<DonHangChiTiet> list = new ArrayList<>();
    private DonHangChiTietDao chiTietDao;
    private adapter_thanh_toan adapterThanhToan;

    public Fragment_confilm_thanhtoan() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfilmThanhtoanBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        chiTietDao = new DonHangChiTietDao(getContext());
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterThanhToan = new adapter_thanh_toan(list, getContext());
                binding.rcv.setAdapter(adapterThanhToan);

            }
        }
        binding.btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_gioHang frgGioHang=new Fragment_gioHang();
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,frgGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}