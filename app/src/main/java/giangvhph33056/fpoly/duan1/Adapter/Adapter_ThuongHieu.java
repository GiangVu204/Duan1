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

import giangvhph33056.fpoly.duan1.DAO.ThuongHieuDAO;
import giangvhph33056.fpoly.duan1.Model.ThuongHieu;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_ThuongHieu extends RecyclerView.Adapter<Adapter_ThuongHieu.ViewHolder>{
    private Context context;
    private ArrayList<ThuongHieu> list;
    ThuongHieuDAO dao;


    public Adapter_ThuongHieu(Context context, ArrayList<ThuongHieu> list) {
        this.context = context;
        this.list = list;
        dao = new ThuongHieuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thuong_hieu,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.MaTH.setText(String.valueOf(list.get(position).getMaTH()));
        holder.SDT.setText(String.valueOf(list.get(position).getSDT()));
        holder.TenTH.setText(String.valueOf(list.get(position).getTenTH()));
        ThuongHieu th = list.get(position);

        holder.TH_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Warning!!!");
                builder.setMessage("Bạn có chắc muốn xóa thương hiệu này chứ!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ThuongHieuDAO dao = new ThuongHieuDAO(context);
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getMaTH());
                        switch (check){
                            case 1:
                                list.clear();
                                list = dao.getDSThuongHieu();
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView MaTH, TenTH, SDT;
        ImageView TH_Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaTH = itemView.findViewById(R.id.MaTH);
            TenTH = itemView.findViewById(R.id.TenTH);
            SDT = itemView.findViewById(R.id.SDT);
            TH_Delete = itemView.findViewById(R.id.TH_Delete);
        }
    }

}
