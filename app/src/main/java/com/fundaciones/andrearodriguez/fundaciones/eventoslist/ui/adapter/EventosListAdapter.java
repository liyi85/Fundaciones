package com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public class EventosListAdapter extends RecyclerView.Adapter<EventosListAdapter.ViewHolder> {

    private List<Eventos> eventosList;
    private ImageLoader imageLoader;
    private OnItemClickListenerEvento clickListener;

    public EventosListAdapter(List<Eventos> eventosList, ImageLoader imageLoader, OnItemClickListenerEvento clickListener) {
        this.eventosList = eventosList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_eventos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Eventos currenEvento = eventosList.get(position);
        imageLoader.load(holder.imgEvento, currenEvento.getUrl());

        holder.txtNombreEvento.setText(currenEvento.getNombre());
        holder.txtLugar.setText(currenEvento.getLugar());
        holder.txtFecha.setText(currenEvento.getFecha());
        holder.txtHora.setText(currenEvento.getHora());
        holder.txtTipo.setText(currenEvento.getTipoevento());

        holder.setOnItemClickListener(currenEvento, clickListener);
        if (currenEvento.isPublishedByMe()) {
            holder.imgDelete.setVisibility(View.VISIBLE);
        } else {
            holder.imgDelete.setVisibility(View.GONE);
        }
    }

    public void addEvento(Eventos eventos) {
        eventosList.add(0, eventos);
        notifyDataSetChanged();
    }

    public void removeEvento(Eventos eventos) {
        eventosList.remove(eventos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgEvento)
        ImageView imgEvento;
        @Bind(R.id.txtNombreEvento)
        TextView txtNombreEvento;
        @Bind(R.id.txtLugar)
        TextView txtLugar;
        @Bind(R.id.txtFecha)
        TextView txtFecha;
        @Bind(R.id.txtHora)
        TextView txtHora;
        @Bind(R.id.txtTipo)
        TextView txtTipo;
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

        public void setOnItemClickListener(final Eventos eventos, final OnItemClickListenerEvento clickListener) {
            imgEvento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onEventoClick(eventos);
                }
            });

            imgShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onShareclick(eventos, imgEvento);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteClick(eventos);
                }
            });
        }
    }
}
