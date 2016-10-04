package com.example.andrearodriguez.fundaciones.libs.base;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface EvenBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
