package com.example.andrearodriguez.fundaciones.domine.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/21/16.
 */

@Singleton
@Component (modules = {DomainModule.class, FundacionAppModule.class})
public interface DomainComponent {
}
