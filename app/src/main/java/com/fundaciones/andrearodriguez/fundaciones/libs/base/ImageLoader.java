package com.fundaciones.andrearodriguez.fundaciones.libs.base;

import android.widget.ImageView;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);

}
