package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.NapTienDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.NapTien;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_Nap_Tien  extends RecyclerView.Adapter<Adapter_Nap_Tien.ViewHolder>{
    private Context context;
    private ArrayList<NapTien> list;
    NapTienDAO dao;
    ThanhVienDAO daotv;
    public Adapter_Nap_Tien(Context context, ArrayList<NapTien> list) {
        this.context = context;
        this.list = list;
        dao = new NapTienDAO(context);
        daotv = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public Adapter_Nap_Tien.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nap_tien, parent, false);
        return new Adapter_Nap_Tien .ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Nap_Tien.ViewHolder holder, int position) {
        NapTien tv = list.get(position);
        holder.txtMaNT_NT.setText(String.valueOf(list.get(position).getMaNT()));
        holder.txtHotenNT_nt.setText(list.get(position).getTenNNT());
        holder.txtNgayNT_tv.setText(list.get(position).getNgayNT());
        holder.txtSoTienNap_nt.setText(String.valueOf(list.get(position).getSotien()));
        String trangthai="";
        if(list.get(position).getTrangthai()==1){
            trangthai = "Đã Hoàn Thành";
            holder.btnxacnhan.setVisibility(View.GONE);


        }else{
            trangthai ="Chưa hoàn Thành";
            holder.btnxacnhan.setVisibility(View.VISIBLE);

        }
        holder.txtTrangThai_tv.setText(trangthai);
        holder.btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean kiemtra = dao.thaydoitrangthai(list.get(holder.getAdapterPosition()).getMaNT());
                if(kiemtra){
                    list.clear();
                    list = dao.selectAllNapTien();
                    notifyDataSetChanged();

                }else{
                    Toast.makeText(context, "Thay đổi trạng thái thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaNT_NT, txtHotenNT_nt, txtNgayNT_tv,txtSoTienNap_nt,txtTrangThai_tv;
        ImageView ImgAnhTV, imgDelete_tv,imgChinhSua_tv;
        Button btnxacnhan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaNT_NT = itemView.findViewById(R.id.txtMaNT_NT);
            txtHotenNT_nt = itemView.findViewById(R.id.txtHotenNT_nt);
            txtNgayNT_tv = itemView.findViewById(R.id.txtNgayNT_tv);
            txtSoTienNap_nt = itemView.findViewById(R.id.txtSoTienNap_nt);
            txtTrangThai_tv = itemView.findViewById(R.id.txtTrangThai_tv);
            btnxacnhan  = itemView.findViewById(R.id.btnxacnhan);

        }
    }
}
