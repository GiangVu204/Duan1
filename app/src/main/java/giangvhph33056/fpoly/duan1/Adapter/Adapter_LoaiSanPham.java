package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.LoaiSanPhamDAO;
import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.KichThuoc;
import giangvhph33056.fpoly.duan1.Model.LoaiSanPham;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_LoaiSanPham extends RecyclerView.Adapter<Adapter_LoaiSanPham.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSanPham> list;
    LoaiSanPhamDAO dao;
    EditText edt_updateTenLoaisp;

    public Adapter_LoaiSanPham(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
        dao = new LoaiSanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_san_pham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.MaLSP.setText(String.valueOf(list.get(position).getMaLSP()));
        holder.TenTh.setText(list.get(position).getTenLSP());
        LoaiSanPham lsp = list.get(position);

        holder.LSP_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning!!!");
                builder.setMessage("Bạn có chắc là muốn xóa loại sản phẩm này chứ!!!");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoaiSanPhamDAO dao = new LoaiSanPhamDAO(context);
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getMaLSP());
                        switch (check){
                            case 1:
                                list.clear();
                                list = dao.getDSLoaiSanPham();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thương hiệu thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không thể xóa vì đang có thương hiệu thuộc sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa thương hiệu không thành công", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView MaLSP, TenTh;
        ImageView LSP_Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            MaLSP = itemView.findViewById(R.id.MaLSP);
            TenTh = itemView.findViewById(R.id.TenLSP);
            LSP_Delete = itemView.findViewById(R.id.LSP_Delete);
        }
    }
    public void opendialogsua(LoaiSanPham lsp){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_loai_san_pham,null);
        builder.setView(view);
        edt_updateTenLoaisp = view.findViewById(R.id.edt_updateTenLoaisp);
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenloaisp =edt_updateTenLoaisp.getText().toString();
                int id = lsp.getMaLSP();
                LoaiSanPham lspu = new LoaiSanPham(id,tenloaisp);
                if (dao.update(lspu)) {
                    list.clear();
                    list.addAll(dao.getDSLoaiSanPham());
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
