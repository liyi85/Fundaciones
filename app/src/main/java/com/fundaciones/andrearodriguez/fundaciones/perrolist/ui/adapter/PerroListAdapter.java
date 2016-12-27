package com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.adapter;

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
 * Created by andrearodriguez on 9/25/16.
 */
public class PerroListAdapter extends RecyclerView.Adapter<PerroListAdapter.ViewHolder> {


    private List<Paticas> perroList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;



    public PerroListAdapter(List<Paticas> perroList, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.perroList = perroList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_perro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Paticas currenPerro = perroList.get(position);
        imageLoader.load(holder.imgDog, currenPerro.getUrl());

        holder.txtNombre.setText(currenPerro.getNombre());
        holder.txtTamano.setText(currenPerro.getTamaño());
        holder.txtSexo.setText(currenPerro.getSexo());
        holder.txtEdad.setText(currenPerro.getEdad());

        holder.setOnItemClickListener(currenPerro, clickListener);
        if (currenPerro.isPublishedByMe()) {
            holder.imgDelete.setVisibility(View.VISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addPerro(Paticas paticas) {
        perroList.add(0, paticas);
        notifyDataSetChanged();
    }

    public void removePerro(Paticas paticas) {
        perroList.remove(paticas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return perroList.size();

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgDog)
        ImageView imgDog;
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

        public void setOnItemClickListener(final Paticas paticas, final OnItemClickListener clickListener) {
            imgDog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onPerroClick(paticas);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(paticas, imgDog);
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