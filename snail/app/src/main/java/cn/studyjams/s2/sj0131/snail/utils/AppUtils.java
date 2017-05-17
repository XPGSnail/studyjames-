package cn.studyjams.s2.sj0131.snail.utils;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.Collection;
import java.util.Map;


public class AppUtils {

    /**
     * 判断集合是否为空
     *
     * @param map
     * @param <V,T>
     * @return
     */
    public static <V, T> boolean isEmpty(Map<V, T> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 判断集合是否为空
     *
     * @param c
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(Collection<V> c) {
        return (c == null || c.size() == 0);
    }


    public static void loadCircleImage(final Fragment context, String url, final ImageView iv) {
        Glide.with(context).load(url).asBitmap().centerCrop()
                .into(new BitmapImageViewTarget(iv) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

}
