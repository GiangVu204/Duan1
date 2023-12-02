package giangvhph33056.fpoly.duan1.Adapter;

import android.annotation.SuppressLint;
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import giangvhph33056.fpoly.duan1.DAO.HoaDonDAO;
import giangvhph33056.fpoly.duan1.DAO.SanPhamDAO;
import giangvhph33056.fpoly.duan1.Model.GioHang;
import giangvhph33056.fpoly.duan1.Model.SanPham;
import giangvhph33056.fpoly.duan1.R;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> gioHangList;
    SanPhamDAO dao;

    public Adapter_GioHang(Context context, ArrayList<SanPham> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
        dao = new SanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy thông tin từ danh sách gioHangList
        SanPham gioHangItem = gioHangList.get(position);

        // Hiển thị thông tin trong View
        holder.item_giohang_tensp.setText(gioHangItem.getTenSP());
        holder.item_giohang_gia.setText(String.valueOf(gioHangItem.getGia()));
        holder.item_giohang_soluong.setText(String.valueOf(gioHangItem.getSoLuong()));
        Picasso.get().load(gioHangItem.getAvataSP()).into(holder.item_giohang_image);
        // Giả sử 'item_giohang_giasp2' là một TextView khác để hiển thị tổng giá
        holder.item_giohang_giasp2.setText(String.valueOf(gioHangItem.getGia() * gioHangItem.getSoLuong()));

        holder.ImgDeleteGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("CẢNH BÁO");// set tieu de
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("BẠN CÓ CHẮC CHẮN MUỐN XÓA HÓA ĐƠN NÀY KHÔNG");
                /// tạo nut buttun yes , xuli su kien cho nut
                builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dao.deletee(gioHangItem.getMaSP())) {
                            gioHangList.clear();
                            gioHangList.addAll(dao.selectAllGioHang());
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
                });
                AlertDialog di = builder.create();
                di.show();
            }
        });

//         Trong Adapter_GioHang
        holder.item_giohang_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = gioHangItem.getSoLuong();
                if (soLuong > 1) {
                    soLuong--; // Giảm số lượng đi 1 đơn vị
                    gioHangItem.setSoLuong(soLuong); // Cập nhật số lượng mới cho sản phẩm
                    holder.item_giohang_soluong.setText(String.valueOf(soLuong)); // Hiển thị số lượng mới

                    // Cập nhật số lượng trong cơ sở dữ liệu
                    dao.updateQuantity(gioHangItem.getMaSP(), soLuong);
                    // Thông báo cho RecyclerView cập nhật lại
                    notifyItemChanged(position);

                }
            }
        });

        holder.item_giohang_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = gioHangItem.getSoLuong();
                soLuong++; // Tăng số lượng lên 1 đơn vị
                gioHangItem.setSoLuong(soLuong); // Cập nhật số lượng mới cho sản phẩm
                holder.item_giohang_soluong.setText(String.valueOf(soLuong)); // Hiển thị số lượng mới

                // Cập nhật số lượng trong cơ sở dữ liệu
                dao.updateQuantity(gioHangItem.getMaSP(), soLuong);
                // Thông báo cho RecyclerView cập nhật lại
                notifyItemChanged(position);
            }
        });

//        // Trong Adapter_GioHang
//        holder.item_giohang_cong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int soLuongTrongGioHang = gioHangItem.getSoLuong();
//                int remainingQuantity = getRemainingQuantity(gioHangItem);
//
//                if (soLuongTrongGioHang < remainingQuantity) {
//                    // Nếu số lượng trong giỏ hàng chưa vượt quá tồn kho
//                    soLuongTrongGioHang++;
//                    gioHangItem.setSoLuong(soLuongTrongGioHang);
//                    holder.item_giohang_soluong.setText(String.valueOf(soLuongTrongGioHang));
//
//                    // Cập nhật số lượng trong cơ sở dữ liệu
//                    dao.updateQuantity(gioHangItem.getMaSP(), soLuongTrongGioHang);
//
//                    // Cập nhật lại giá trị remainingQuantity
//                    remainingQuantity = getRemainingQuantity(gioHangItem);
//
//                    // Thông báo cho RecyclerView cập nhật lại
//                    notifyItemChanged(position);
//                } else {
//                    // Nếu số lượng trong giỏ hàng đã vượt quá tồn kho
//                    Toast.makeText(context, "Số lượng sản phẩm đã quá tồn kho", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//// Trong Adapter_GioHang
//        holder.item_giohang_tru.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int soLuongTrongGioHang = gioHangItem.getSoLuong();
//                if (soLuongTrongGioHang > 0) {
//                    soLuongTrongGioHang--;
//                    gioHangItem.setSoLuong(soLuongTrongGioHang);
//                    holder.item_giohang_soluong.setText(String.valueOf(soLuongTrongGioHang));
//
//                    // Cập nhật số lượng trong cơ sở dữ liệu
//                    dao.updateQuantity(gioHangItem.getMaSP(), soLuongTrongGioHang);
//
//                    // Cập nhật lại giá trị remainingQuantity
//                    int remainingQuantity = getRemainingQuantity(gioHangItem);
//
//                    // Thông báo cho RecyclerView cập nhật lại
//                    notifyItemChanged(position);
//                }
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_giohang_image, ImgDeleteGH, item_giohang_tru, item_giohang_cong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            ImgDeleteGH = itemView.findViewById(R.id.ImgDeleteGH);
            item_giohang_tru = itemView.findViewById(R.id.item_giohang_tru);
            item_giohang_cong = itemView.findViewById(R.id.item_giohang_cong);
        }
    }

    // Trong Adapter_GioHang
    private int getRemainingQuantity(SanPham gioHangItem) {
        int soLuongSanPham = gioHangItem.getSoLuong();
        int soLuongTrongGioHang = gioHangItem.getSoLuong();
        return soLuongSanPham - soLuongTrongGioHang;
    }

}
