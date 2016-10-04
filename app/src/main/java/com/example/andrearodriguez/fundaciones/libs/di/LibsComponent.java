package com.example.andrearodriguez.fundaciones.libs.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Singleton
@Component(modules = {LibsModule.class, FundacionAppModule.class})
public interface LibsComponent {
}
