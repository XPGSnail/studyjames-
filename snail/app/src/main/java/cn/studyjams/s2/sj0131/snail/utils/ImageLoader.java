package cn.studyjams.s2.sj0131.snail.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import cn.studyjams.s2.sj0131.snail.R;


public class ImageLoader {

    public static void loadImage(String url, ImageView imageView, Context context) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    public static Bitmap loadImageBitmap(String url, Context context) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            e.printStackTrace();
        }
        return bitmap;
    }
}
