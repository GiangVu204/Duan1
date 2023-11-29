package giangvhph33056.fpoly.duan1.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import giangvhph33056.fpoly.duan1.Adapter.Adapter_SanPham;
import giangvhph33056.fpoly.duan1.Adapter.Adapter_ThanhVien;
import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.DAO.LoaiSanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;
import giangvhph33056.fpoly.duan1.R;
import giangvhph33056.fpoly.duan1.sanphamchitiet;

public class Fragment_san_pham extends Fragment {
    SanPhamDAO spDAO;
    Adapter_SanPham adaptersp;
    RecyclerView rcv_sanpham;
    private ArrayList<SanPham> list = new ArrayList<>();
    FloatingActionButton fltadd;
    Spinner spnloaisp_add,spnthuong_add,spnKichthuoc_add;
    EditText edtTensp_sp_add,edtgia_sp_add,edtSoLuong_sp_add;
    public Fragment_san_pham() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_san_pham, container, false);
        rcv_sanpham = view.findViewById(R.id.rcv_sanpham);
        fltadd = view.findViewById(R.id.fltadd);
       loaddata();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogadd();
            }
        });
        adaptersp.setClick(new Adapter_SanPham.click() {
            @Override
            public void click(int pos) {
                SanPham hienthi = list.get(pos);
                Intent intent = new Intent(getContext(), sanphamchitiet.class);
                intent.putExtra("sanphamct",hienthi);
                startActivity(intent);
            }
        });
        // phân quyền
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DANGNHAPTV", Context.MODE_PRIVATE);
        String Loai = sharedPreferences.getString("Loai","");
        if(Loai.equalsIgnoreCase("admin")){
            fltadd.setVisibility(view.VISIBLE);

        }
        if(Loai.equalsIgnoreCase("Nhân Viên")){
            fltadd.setVisibility(view.VISIBLE);



        }
        if(Loai.equalsIgnoreCase("Khách Hàng")){
            fltadd.setVisibility(view.GONE);

        }
        return view;
    }
    private  void loaddata(){
        /// setlayout
        spDAO= new SanPhamDAO(getContext());
        list = spDAO.selectAllSanPham();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_sanpham.setLayoutManager(linearLayoutManager);
        rcv_sanpham.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adaptersp = new Adapter_SanPham(getContext(), list);
        rcv_sanpham.setAdapter(adaptersp);

    }
    private void dialogadd (){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_sanpham, null);
        builder.setView(view);
        spnKichthuoc_add = view.findViewById(R.id.spnKichthuoc_add);
        spnthuong_add = view.findViewById(R.id.spnthuong_add);
        spnloaisp_add = view.findViewById(R.id.spnloaisp_add);
        edtTensp_sp_add = view.findViewById(R.id.edtTensp_sp_add);
        edtgia_sp_add = view.findViewById(R.id.edtgia_sp_add);
        getDataKichThuoc(spnKichthuoc_add);
        getDataLoai(spnloaisp_add);
        getDataThuong(spnthuong_add);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 //lay ma Kich Thuoc
                HashMap<String , String> hsKT = (HashMap<String, String>) spnKichthuoc_add.getSelectedItem();
                int id= Integer.parseInt(hsKT.get("id"));
                int sluong = Integer.parseInt(hsKT.get("SoLuong"));
                // lay ma thuong hiêu
                HashMap<String , String> hsTH = (HashMap<String, String>) spnthuong_add.getSelectedItem();
                int MaTH= Integer.parseInt(hsTH.get("MaTH"));
                // lay ma loai san pham
                HashMap<String , String> hsLSP = (HashMap<String, String>) spnloaisp_add.getSelectedItem();
                int MaLSP= Integer.parseInt(hsLSP.get("MaLSP"));
                String tensp = edtTensp_sp_add.getText().toString();
                int gia = Integer.parseInt(edtgia_sp_add.getText().toString());
                //int soluong = Integer.parseInt(edtSoLuong_sp_add.getText().toString());
                themPhieuMuon(tensp,gia,sluong,id,MaTH,MaLSP);

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void getDataKichThuoc(Spinner spnKichthuoc_add) {
        KichThuocDAO DAO = new KichThuocDAO(getContext());
        ArrayList<KichThuoc> list = DAO.selectAllKichThuoc();
        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
        for ( KichThuoc s : list) {
            HashMap<String , String> hs = new HashMap<>();
            hs.put("id" , String.valueOf(s.getId()));
            hs.put("MaKT" , s.getMaKT());
            hs.put("Size", String.valueOf("Size "+ s.getSize()));
            hs.put("SoLuong", String.valueOf(s.getSoLuong()));
            listHM.add(hs);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(),listHM,android.R.layout.simple_list_item_1,new String[]{"MaKT"}, new int[]{android.R.id.text1});
        spnKichthuoc_add.setAdapter(adapter);
    }
    private void getDataLoai(Spinner spnloaisp_add) {
        LoaiSanPhamDAO DAO = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list = DAO.getDSLoaiSanPham();
        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
        for ( LoaiSanPham s : list) {
            HashMap<String , String> hs = new HashMap<>();
            hs.put("MaLSP" , String.valueOf(s.getMaLSP()));
            hs.put("TenLSP", s.getTenLSP());
            listHM.add(hs);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(),listHM,android.R.layout.simple_list_item_1,new String[]{"TenLSP"}, new int[]{android.R.id.text1});
        spnloaisp_add.setAdapter(adapter);
    }

    private void getDataThuong(Spinner spnthuong_add) {
        ThuongHieuDAO DAO = new ThuongHieuDAO(getContext());
        ArrayList<ThuongHieu> list = DAO.getDSThuongHieu();
        ArrayList<HashMap<String,String>> listHM = new ArrayList<>();
        for (ThuongHieu tv : list) {
            HashMap<String , String> hs = new HashMap<>();
            hs.put("MaTH" , String.valueOf(tv.getMaTH()));
            hs.put("TenTH", tv.getTenTH());
            hs.put("SDT",String.valueOf(tv.getSDT()));
            listHM.add(hs);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(),listHM,android.R.layout.simple_list_item_1,new String[]{"TenTH"}, new int[]{android.R.id.text1});
        spnthuong_add.setAdapter(adapter);
    }
    private void themPhieuMuon(String tensp , int gia,int soluong ,int id ,int MaTH  ,int MaLSP ){
        SanPham  sp = new SanPham( tensp ,gia,soluong,id, MaTH,MaLSP);
        boolean kiemtra =spDAO.insert(sp);
        if (kiemtra == true){
            Toast.makeText(getContext(), "Thêm sản phẩm thành  thành công!", Toast.LENGTH_SHORT).show();
            loaddata();
        }else{
            Toast.makeText(getContext(), "Thêm Sản phẩm thất bại!", Toast.LENGTH_SHORT).show();
        }

    }

}