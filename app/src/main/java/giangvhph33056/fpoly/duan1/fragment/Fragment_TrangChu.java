package giangvhph33056.fpoly.duan1.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import giangvhph33056.fpoly.duan1.R;

public class Fragment_TrangChu extends Fragment {
    TextView txttennguoidung_tt;

    public Fragment_TrangChu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__trang_chu, container, false);
        txttennguoidung_tt = view.findViewById(R.id.txttennguoidung_tt);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DANGNHAPTV", Context.MODE_PRIVATE);
        String loai = sharedPreferences.getString("HoTen","");
        txttennguoidung_tt.setText(loai);

        return view;
    }
}