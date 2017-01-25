package com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter;

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
 * Created by andrearodriguez on 1/24/17.
 */

public class OtrostListAdapter extends RecyclerView.Adapter<OtrostListAdapter.ViewHolder> {


    private List<Paticas> otrosList;
    private ImageLoader imageLoader;
    private OnItemClickListenerOtros clickListener;

    public OtrostListAdapter(List<Paticas> otrosList, ImageLoader imageLoader, OnItemClickListenerOtros clickListener) {
        this.otrosList = otrosList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_otros, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Paticas currenOtro = otrosList.get(position);
        imageLoader.load(holder.imgOtro, currenOtro.getUrl());

        holder.txtNombre.setText(currenOtro.getNombre());
        holder.txtTamano.setText(currenOtro.getTamaño());
        holder.txtSexo.setText(currenOtro.getSexo());
        holder.txtEdad.setText(currenOtro.getEdad());

        holder.setOnItemClickListener(currenOtro, clickListener);
        if (currenOtro.isPublishedByMe()) {
            holder.imgDelete.setVisibility(View.VISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addOtros(Paticas paticas) {
        otrosList.add(0, paticas);
        notifyDataSetChanged();
    }

    public void removeOtros(Paticas paticas) {
        otrosList.remove(paticas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return otrosList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgOtro)
        ImageView imgOtro;
        @Bind(R.id.txtNombre)
        TextView txtNombre;
        @Bind(R.id.txtEspecie)
        TextView txtEspecie;
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

        public void setOnItemClickListener(final Paticas paticas, final OnItemClickListenerOtros clickListener) {
            imgOtro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onOtrosClick(paticas);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(paticas, imgOtro);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteClick(paticas);
                }
            });
        }
    }
}
