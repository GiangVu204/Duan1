package giangvhph33056.fpoly.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import giangvhph33056.fpoly.duan1.R;
public class Fragment_thanh_vien extends Fragment {
    public Fragment_thanh_vien() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        return view;
    }
}