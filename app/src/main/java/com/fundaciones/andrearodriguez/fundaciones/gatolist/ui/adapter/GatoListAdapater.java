package com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 12/20/16.
 */

public class GatoListAdapater extends RecyclerView.Adapter<GatoListAdapater.ViewHolder> {


    private List<Paticas> gatoList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public GatoListAdapater(List<Paticas> gatoList, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.gatoList = gatoList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gato, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Paticas currenGato = gatoList.get(position);

        imageLoader.load(holder.imgCat, currenGato.getUrl());

        holder.txtNombre.setText(currenGato.getNombre());
        holder.txtTamano.setText(currenGato.getTamaño());
        holder.txtSexo.setText(currenGato.getSexo());
        holder.txtEdad.setText(currenGato.getEdad());

        holder.setOnItemClickListener(currenGato, clickListener);
        if (currenGato.isPublishedByMe()) {
            holder.imgDelete.setVisibility(View.VISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addGato(Paticas paticas) {
        gatoList.add(0, paticas);
        notifyDataSetChanged();
    }

    public void removeGato(Paticas paticas) {
        gatoList.remove(paticas);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return gatoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgCat)
        ImageView imgCat;
        @Bind(R.id.txtNombre)
        TextView txtNombre;
        @Bind(R.id.txtSexo)
        TextView txtSexo;
        @Bind(R.id.txtEdad)
        TextView txtEdad;
        @Bind(R.id.txtTamano)
        TextView txtTamano;
        @Bind(R.id.imgShare)
        ImageButton imgShare;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;
        @Bind(R.id.cv)
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Paticas currenGato, final OnItemClickListener clickListener) {
            imgCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onGatoClick(currenGato);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(currenGato, imgCat);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteClick(currenGato);
                }
            });
        }
    }
}
