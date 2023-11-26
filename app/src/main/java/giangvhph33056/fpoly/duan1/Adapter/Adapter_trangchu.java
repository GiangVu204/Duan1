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
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_trangchu extends RecyclerView.Adapter<Adapter_trangchu.ViewHolder>{
    private Context context;
    private ArrayList<SanPham> list;
    SanPhamDAO dao;
    public interface click{
        void click (int pos);
    }
    private Adapter_SanPham.click click;
    public void setClick(Adapter_SanPham.click click1){
        click = click1;
    }
    public Adapter_trangchu(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        dao = new SanPhamDAO(context);
    }
    @NonNull
    @Override
    public Adapter_trangchu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham_tt, parent, false);
        return new Adapter_trangchu.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_trangchu.ViewHolder holder, int position) {
        holder.txttensp_tt.setText(list.get(position).getTenSP());
        holder.txtgiasp_tt.setText(String.valueOf(list.get(position).getGia()));
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
        TextView txtgiasp_tt,txttensp_tt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgiasp_tt = itemView.findViewById(R.id.txtgiasp_tt);
            txttensp_tt = itemView.findViewById(R.id.txttensp_tt);
        }
    }
}
