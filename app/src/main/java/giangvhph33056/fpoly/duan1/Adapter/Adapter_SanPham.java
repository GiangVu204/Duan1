package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_SanPham extends RecyclerView.Adapter<Adapter_SanPham.ViewHolder>{
    private Context context;
    private ArrayList<SanPham> list;
    SanPhamDAO dao;
    public interface click{
        void click (int pos);
    }
    private click click;
    public void setClick(click click1){
        click = click1;
    }
    public Adapter_SanPham(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        dao = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public Adapter_SanPham.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_SanPham.ViewHolder holder, int position) {
        holder.txttensamphan_sp.setText(list.get(position).getTenSP());
        holder.txtgiasp_sp.setText(String.valueOf(list.get(position).getGia()));
        holder.txtTenthuonghieu_sp.setText(list.get(position).getTenthuonghieu());
        holder.imgDelete_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");// set tieu de
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("BẠN CÓ CHẮC CHẮN MUỐN XÓA SẢN PHẨM NÀY KHÔNG");
                /// tạo nut buttun yes , xuli su kien cho nut
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check =dao.delete(list.get(holder.getAdapterPosition()).getMaSP());
                        if (check ==1){
                            list.clear();
                            list.addAll(dao.selectAllSanPham());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        }else if (check == -1) {
                            Toast.makeText(context, "Không thể xóa (Sản phẩm) này vì đã có (Hóa đơn) thuộc thể loại này", Toast.LENGTH_SHORT).show();
                        }else {
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click != null){
                    click.click(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensamphan_sp,txtgiasp_sp,txtTenthuonghieu_sp;
        ImageView imgDelete_sp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete_sp = itemView.findViewById(R.id.imgDelete_sp);
            txttensamphan_sp = itemView.findViewById(R.id.txttensamphan_sp);
            txtgiasp_sp = itemView.findViewById(R.id.txtgiasp_sp);
            txtTenthuonghieu_sp = itemView.findViewById(R.id.txtTenthuonghieu_sp);

        }
    }
}
