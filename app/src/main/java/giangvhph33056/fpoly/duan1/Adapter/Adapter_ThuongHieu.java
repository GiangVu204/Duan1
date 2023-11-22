package giangvhph33056.fpoly.duan1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thuong_hieu, parent, false);
        return new  ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.MaTH.setText(String.valueOf(list.get(position).getMaTH()));
        holder.SDT.setText(list.get(position).getSDT());
        holder.TenTH.setText(list.get(position).getTenTH());
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogUpdateTH(th);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView MaTH, TenTH, SDT;
        ImageView ImgAnh, TH_Delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MaTH = itemView.findViewById(R.id.MaTH);
            TenTH = itemView.findViewById(R.id.TenTH);
            SDT = itemView.findViewById(R.id.SDT);
            ImgAnh = itemView.findViewById(R.id.ImgAnh);
            TH_Delete = itemView.findViewById(R.id.TH_Delete);

        }
    }

    @SuppressLint("MissingInflatedId")
    private void dialogUpdateTH(ThuongHieu th) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_thuonghieu,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_MaTH = view.findViewById(R.id.in_updateMaTH);
        TextInputEditText ed_MaTH = view.findViewById(R.id.ed_updateMaTH);
        TextInputLayout in_SDT = view.findViewById(R.id.in_updateSDT);
        TextInputEditText ed_SDT = view.findViewById(R.id.ed_updateSDT);
        TextInputLayout in_TenTH = view.findViewById(R.id.in_updateTenTH);
        TextInputEditText ed_TenTH = view.findViewById(R.id.ed_updateTenTH);
        ImageView ImgAnhh = view.findViewById(R.id.ImgAnhh);
        Button UpdateTH = view.findViewById(R.id.TH_update);
        Button CancelTH = view.findViewById(R.id.TH_Cancelupdtae);

        ed_MaTH.setText(String.valueOf(th.getMaTH()));
        ed_SDT.setText(th.getSDT());
        ed_TenTH.setText(th.getTenTH());

        ed_SDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_SDT.setError("Vui lòng không để trống Số điện thoại");
                }else {
                    in_SDT.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_TenTH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    in_TenTH.setError("Vui lòng không để trống Tên thương hiệu");
                }else {
                    in_TenTH.setError(null);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        UpdateTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                th.setSDT(ed_SDT.getText().toString());
                String SDT = ed_SDT.getText().toString();
                //
                th.setTenTH(ed_TenTH.getText().toString());
                String TenTH = ed_TenTH.getText().toString();

                if (SDT.isEmpty() || TenTH.isEmpty()){
                    if (SDT.equals("")){
                        in_SDT.setError("Vui lòng nhập thương hiệu");
                    }else {
                        in_SDT.setError(null);
                    }
                    if (TenTH.equals("")){
                        in_TenTH.setError("Vui lòng nhập tên thương hiệu");
                    }else {
                        in_TenTH.setError(null);
                    }
                }else {
                    if (dao.update(th)){
                        list.clear();
                        list.addAll(dao.getDSThuongHieu());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Update thành công!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Update thất bại!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        CancelTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_SDT.setText("");
                ed_TenTH.setText("");
            }
        });
    }

}
