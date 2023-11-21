package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.KichThuocDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.R;


public class Adapter_KichThuoc extends RecyclerView.Adapter<Adapter_KichThuoc.ViewHolder>{
    private Context context;
    private ArrayList<KichThuoc> list;
    KichThuocDAO dao;
    TextInputEditText edtsize_kt,edtSoluong_kt;
    public Adapter_KichThuoc(Context context, ArrayList<KichThuoc> list) {
        this.context = context;
        this.list = list;
        dao = new KichThuocDAO(context);
    }
    @NonNull
    @Override
    public Adapter_KichThuoc.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_kich_thuoc,parent, false);
        return new Adapter_KichThuoc.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_KichThuoc.ViewHolder holder, int position) {
        holder.txtMaKT_kt.setText(String.valueOf(list.get(position).getMaKT()));
        holder.txtSize_kt.setText("SIZE " + list.get(position).getSize());
        holder.txtSoLuong_kt.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.imgDelete_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");// set tieu de
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("BẠN CÓ CHẮC CHẮN MUỐN XÓA LOẠI SÁCH NÀY KHÔNG");
                /// tạo nut buttun yes , xuli su kien cho nut
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check =dao.delete(list.get(holder.getAdapterPosition()).getMaKT());
                        if (check ==1){
                            list.clear();
                            list.addAll(dao.selectAllKichThuoc());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        }else if (check == -1) {
                            Toast.makeText(context, "Không thể xóa (kich thước) này vì đã có (sản phẩm) thuộc thể loại này", Toast.LENGTH_SHORT).show();
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                opendialogsua(list.get(holder.getAdapterPosition()));
                return true;
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaKT_kt, txtSize_kt, txtSoLuong_kt;
        ImageView imgDelete_kt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKT_kt = itemView.findViewById(R.id.txtMaKT_kt);
            txtSize_kt = itemView.findViewById(R.id.txtSize_kt);
            txtSoLuong_kt = itemView.findViewById(R.id.txtSoLuong_kt);
            imgDelete_kt = itemView.findViewById(R.id.imgDelete_kt);
        }
    }
    public void opendialogsua(KichThuoc kt){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_kich_thuoc_update,null);
        builder.setView(view);
        edtsize_kt = view.findViewById(R.id.edtsize_kt);
        edtSoluong_kt = view.findViewById(R.id.edtSoluong_kt);
        edtsize_kt.setText(String.valueOf(kt.getSize()));
        edtSoluong_kt.setText(String.valueOf(kt.getSoLuong()));
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = Integer.parseInt(edtsize_kt.getText().toString());
                int Soluong =Integer.parseInt(edtSoluong_kt.getText().toString());
                int id = kt.getMaKT();
                KichThuoc kt = new KichThuoc(id,size,Soluong);
                if (dao.update(kt)) {
                    list.clear();
                    list.addAll(dao.selectAllKichThuoc());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "UPDATE thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "UPDATE that bai", Toast.LENGTH_SHORT).show();
                }
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

}
