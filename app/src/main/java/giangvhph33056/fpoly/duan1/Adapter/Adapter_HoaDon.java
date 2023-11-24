package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.HoaDonDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.Model.HoaDon;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_HoaDon extends RecyclerView.Adapter<Adapter_HoaDon.ViewHolder>{
    private Context context;
    private ArrayList<HoaDon> list;
    HoaDonDAO dao;
    public Adapter_HoaDon(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        dao = new HoaDonDAO(context);
    }

    @NonNull
    @Override
    public Adapter_HoaDon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_HoaDon.ViewHolder holder, int position) {
        holder.txtMahd_hd.setText(String.valueOf(list.get(position).getMaHD()));
        holder.txtngaylhd_hd.setText(list.get(position).getNgayDH());
        holder.txttrangthai_hd.setText(String.valueOf(list.get(position).getTrangThai()));
        holder.txtsoluong_hd.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.txtgia_hd.setText(String.valueOf(list.get(position).getGia()));
        holder.txtTenthanhvien_hd.setText(String.valueOf(list.get(position).getTentv()));
        holder.txttensamphan_hd.setText(String.valueOf(list.get(position).getTensp()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMahd_hd, txtngaylhd_hd, txttrangthai_hd, txtsoluong_hd, txtgia_hd, txtTenthanhvien_hd, txttensamphan_hd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMahd_hd = itemView.findViewById(R.id.txtMahd_hd);
            txtngaylhd_hd = itemView.findViewById(R.id.txtngaylhd_hd);
            txttrangthai_hd = itemView.findViewById(R.id.txttrangthai_hd);
            txtsoluong_hd = itemView.findViewById(R.id.txtsoluong_hd);
            txtgia_hd = itemView.findViewById(R.id.txtgia_hd);
            txtTenthanhvien_hd = itemView.findViewById(R.id.txtTenthanhvien_hd);
            txttensamphan_hd = itemView.findViewById(R.id.txttensamphan_hd);


        }
    }

}
