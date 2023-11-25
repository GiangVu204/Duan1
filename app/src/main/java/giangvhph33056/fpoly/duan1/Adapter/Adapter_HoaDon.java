package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        HoaDon hd = list.get(position);
        holder.hd_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");// set tieu de
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("BẠN CÓ CHẮC CHẮN MUỐN XÓA HÓA ĐƠN NÀY KHÔNG");
                /// tạo nut buttun yes , xuli su kien cho nut
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dao.delete(hd.getMaHD())) {
                            list.clear();
                            list.addAll(dao.selectAllHoaDon());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "XÓA THẤT BẠI", Toast.LENGTH_SHORT).show();
                    }
                }) ;
                AlertDialog di = builder.create();
                di.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hd_Delete;
        TextView txtMahd_hd, txtngaylhd_hd, txttrangthai_hd, txtsoluong_hd, txtgia_hd, txtTenthanhvien_hd, txttensamphan_hd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMahd_hd = itemView.findViewById(R.id.txtMahd_hd);
            hd_Delete = itemView.findViewById(R.id.TH_Delete);
            txtngaylhd_hd = itemView.findViewById(R.id.txtngaylhd_hd);
            txttrangthai_hd = itemView.findViewById(R.id.txttrangthai_hd);
            txtsoluong_hd = itemView.findViewById(R.id.txtsoluong_hd);
            txtgia_hd = itemView.findViewById(R.id.txtgia_hd);
            txtTenthanhvien_hd = itemView.findViewById(R.id.txtTenthanhvien_hd);
            txttensamphan_hd = itemView.findViewById(R.id.txttensamphan_hd);


        }
    }

}
