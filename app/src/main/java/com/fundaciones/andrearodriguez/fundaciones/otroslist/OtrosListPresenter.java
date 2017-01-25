package com.fundaciones.andrearodriguez.fundaciones.otroslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.events.OtrosListEvent;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public interface OtrosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeOtros(Paticas paticas);
    void onEventMainThread (OtrosListEvent event);
}
