package giangvhph33056.fpoly.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.ThanhVienDAO;
import giangvhph33056.fpoly.duan1.Model.ThanhVien;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_ThanhVien  extends RecyclerView.Adapter<Adapter_ThanhVien.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;
    ThanhVienDAO dao;
    public Adapter_ThanhVien(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        dao = new ThanhVienDAO(context);
    }


    @NonNull
    @Override
    public Adapter_ThanhVien.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanh_vien, parent, false);
        return new  ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_ThanhVien.ViewHolder holder, int position) {
        holder.txtMaTV_tv.setText(list.get(position).getMaTV());
        holder.txtHoten_tv.setText(list.get(position).getHoTen());
        holder.txtSDT_tv.setText(String.valueOf(list.get(position).getSDT()));
        holder.txtEmail_tv.setText(list.get(position).getEmail());
        holder.txtDchi_tv.setText(list.get(position).getDChi());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaTV_tv, txtHoten_tv, txtSDT_tv,txtEmail_tv,txtDchi_tv;
        ImageView ImgAnh, imgDelete_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTV_tv = itemView.findViewById(R.id.txtMaTV_tv);
            txtHoten_tv = itemView.findViewById(R.id.txtHoten_tv);
            txtSDT_tv = itemView.findViewById(R.id.txtSDT_tv);
            txtEmail_tv = itemView.findViewById(R.id.txtEmail_tv);
            txtDchi_tv = itemView.findViewById(R.id.txtDchi_tv);
            ImgAnh = itemView.findViewById(R.id.ImgAnh);
            imgDelete_tv = itemView.findViewById(R.id.imgDelete_tv);

        }
    }
}
