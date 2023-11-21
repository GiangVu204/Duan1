package giangvhph33056.fpoly.duan1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_KichThuoc;
import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
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
        ktDAO= new KichThuocDAO(getContext());
        list = ktDAO.selectAllKichThuoc();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_kichthuoc.setLayoutManager(linearLayoutManager);
        rcv_kichthuoc.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapterkt = new Adapter_KichThuoc(getContext(), list);
        rcv_kichthuoc.setAdapter(adapterkt);

        return view;
    }
}