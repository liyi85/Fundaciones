package com.example.andrearodriguez.fundaciones.libs.base;

import java.io.File;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
    void delete(File file, String id, ImageStorageFinishedListener listener);
}
