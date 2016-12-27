package com.fundaciones.andrearodriguez.fundaciones.libs;

import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class GreenRobotEventBus implements EvenBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
