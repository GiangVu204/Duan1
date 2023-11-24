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
        holder.txtMasp_sp.setText(String.valueOf(list.get(position).getMaSP()));
        holder.txttensamphan_sp.setText(list.get(position).getTenSP());
        holder.txtgiasp_sp.setText(String.valueOf(list.get(position).getGia()));
        holder.txtsoluong_sp.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.txtmakichthuoc_sp.setText("SIZE "+list.get(position).getSize());
        holder.txtTenthuonghieu_sp.setText(list.get(position).getTenthuonghieu());
        holder.txtloaisp_sp.setText(list.get(position).getTenlsp());
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
        TextView txtMasp_sp,txttensamphan_sp,txtgiasp_sp,txtsoluong_sp,txtmakichthuoc_sp,txtTenthuonghieu_sp,txtloaisp_sp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasp_sp = itemView.findViewById(R.id.txtMasp_sp);
            txttensamphan_sp = itemView.findViewById(R.id.txttensamphan_sp);
            txtgiasp_sp = itemView.findViewById(R.id.txtgiasp_sp);
            txtsoluong_sp = itemView.findViewById(R.id.txtsoluong_sp);
            txtmakichthuoc_sp = itemView.findViewById(R.id.txtmakichthuoc_sp);
            txtTenthuonghieu_sp = itemView.findViewById(R.id.txtTenthuonghieu_sp);
            txtloaisp_sp = itemView.findViewById(R.id.txtloaisp_sp);

        }
    }
}
