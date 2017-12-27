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
import com.example.nkumala.findbanten.model.TokoModel;

import java.util.ArrayList;
import java.util.List;


public class TokoAdapter extends RecyclerView.Adapter<TokoAdapter.ViewHolder>{
    List<TokoModel> tokoModelList= new ArrayList<>();
    Context context;
    OnClickListener mOnClickListener;
    String IMAGE_URL="http://192.168.43.226:8000/images/toko/";
    public interface OnClickListener{
        public void onItemClick(int position);
    }

    public TokoAdapter(List<TokoModel> tokoModelList, Context context,OnClickListener mOnClickListener) {
        this.tokoModelList = tokoModelList;
        this.context = context;
        this.mOnClickListener=mOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.row_toko,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TokoModel tokoModel= tokoModelList.get(position);
        holder.txtName.setText(tokoModel.getNama_toko());
        Glide.with(this.context).load(IMAGE_URL+tokoModel.getGambar_toko()).into(holder.imgToko);
    }

    @Override
    public int getItemCount() {
        return tokoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView imgToko;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txt_row_toko);
            imgToko=itemView.findViewById(R.id.img_row_toko);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
