package com.fundaciones.andrearodriguez.fundaciones.otroslist.events;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 1/24/17.
 */
public class OtrosListEvent {
    private int type;
    private Paticas paticas;
    private String error;

    public final static int READ_EVENT = 0;
    public final static int DELETE_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Paticas getPaticas() {
        return paticas;
    }

    public void setPaticas(Paticas paticas) {
        this.paticas = paticas;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
