package com.example.gmomedia.listviewsample;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by usr0200475 on 15/04/17.
 */
public class LruCacheSample implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mMemoryCache;

    LruCacheSample(){
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;       // 最大メモリに依存
        // int cacheSize = 5  1024  1024;  // 5MB

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 使用キャッシュサイズ(KB単位)
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    // ImageCacheのインターフェイス実装
    @Override
    public Bitmap getBitmap(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mMemoryCache.put(url,bitmap);
    }
}