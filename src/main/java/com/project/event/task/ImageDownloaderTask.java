package com.project.event.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;

import java.lang.ref.WeakReference;

public class ImageDownloaderTask extends AsyncTask<String,Void,Bitmap> {

    private final WeakReference<ImageView> imageViewWeakReference;
    private String url;
    private LruCache<String,Bitmap> lruCache;
    public ImageDownloaderTask(ImageView imageView, LruCache<String,Bitmap> lruCache)
    {
        imageViewWeakReference=new WeakReference<ImageView>(imageView);
        this.lruCache=lruCache;

    }
    @Override
    protected Bitmap doInBackground(String... params) {
        url=params[0];
        return Utilities.downloadBitmap(params[0]);
    }

    public String getUrl()
    {
        return url;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(isCancelled())
        {
            bitmap=null;
        }

        if(imageViewWeakReference!=null)
        {
            ImageView imageView=imageViewWeakReference.get();
            if(imageView!=null)
            {
                lruCache.put(url,bitmap);                   
                Logger.log("beta", "Caching bitmap");

                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
