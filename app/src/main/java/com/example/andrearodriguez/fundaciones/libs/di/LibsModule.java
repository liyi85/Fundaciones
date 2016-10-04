package com.example.andrearodriguez.fundaciones.libs.di;

import android.app.Activity;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.example.andrearodriguez.fundaciones.libs.ClaudinaryImageStorage;
import com.example.andrearodriguez.fundaciones.libs.GlideImageLoader;

import com.example.andrearodriguez.fundaciones.libs.GreenRobotEventBus;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.libs.base.ImageLoader;
import com.example.andrearodriguez.fundaciones.libs.base.ImageStorage;


import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Module
public class LibsModule {
    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }
    @Provides
    @Singleton
    EvenBus providesEvenBus (EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }


    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return  this.activity;
    }
    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary){
        return new ClaudinaryImageStorage(cloudinary);
    }
    @Provides
    @Singleton
    Cloudinary providesCloudinary (Context context){
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }
}
