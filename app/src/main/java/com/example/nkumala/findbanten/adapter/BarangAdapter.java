package com.example.nkumala.findbanten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nkumala.findbanten.R;
import com.example.nkumala.findbanten.model.BarangModel;

import java.util.List;

/**
 * Created by NKumala on 12/28/2017.
 */

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder>{
    List<BarangModel> listBarang;
    Context context;
    String IMAGE_URL="http://192.168.43.226:8000/images/barang/";
    public BarangAdapter(List<BarangModel> listBarang, Context context) {
        this.listBarang = listBarang;
        this.context = context;
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onCLick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_barang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BarangModel barangModel=listBarang.get(position);
        String harga="Rp."+String.valueOf(barangModel.getHarga());
        holder.txtNama.setText(barangModel.getNama_barang());
        holder.txtHarga.setText(harga);
        Glide.with(this.context).load(IMAGE_URL+barangModel.getGambar()).into(holder.imgBarang);
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBarang;
        TextView txtNama,txtHarga;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBarang=itemView.findViewById(R.id.img_barang);
            txtNama=itemView.findViewById(R.id.txt_nama_barang);
            txtHarga=itemView.findViewById(R.id.txt_harga_barang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onCLick(getAdapterPosition());
                }
            });
        }
    }
}
