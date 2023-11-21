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

import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThuongHieu;
import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;
import giangvhph33056.fpoly.duan1.R;

public class Fragment_thuong_hieu extends Fragment {

    FloatingActionButton fltAdd;
    RecyclerView rcvTH;
    ThuongHieuDAO THdao;
    public Fragment_thuong_hieu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuong_hieu, container, false);

        rcvTH = view.findViewById(R.id.rcvThuonghieu);
        fltAdd = view.findViewById(R.id.fltadd);
        THdao = new ThuongHieuDAO(getContext());
        loadData();
        return view;
    }

    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvTH.setLayoutManager(layoutManager);
        ArrayList<ThuongHieu> list = THdao.getDSThuongHieu();
        Adapter_ThuongHieu adapter = new Adapter_ThuongHieu(getContext(),list);
        rcvTH.setAdapter(adapter);
    }
}