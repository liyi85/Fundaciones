package com.fundaciones.andrearodriguez.fundaciones.perrolist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.events.PerroListEvent;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface PerroListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePerro(Paticas paticas);
    void onEventMainThread (PerroListEvent event);
}
