package com.fundaciones.andrearodriguez.fundaciones.eventoslist.events;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

/**
 * Created by andrearodriguez on 1/25/17.
 */
public class EventosListEvent {

    private int type;
    private Eventos eventos;
    private String error;

    public final static int READ_EVENT = 0;
    public final static int DELETE_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
