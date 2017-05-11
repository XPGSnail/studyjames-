package cn.studyjams.s2.sj0131.common;

import android.app.Application;
import android.os.Environment;

import java.io.File;

/**
 * Created by hasee on 2017/5/6.
 */

public class CommonApplication extends Application {

    private static CommonApplication mInstance;

    public static CommonApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
    }


    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
