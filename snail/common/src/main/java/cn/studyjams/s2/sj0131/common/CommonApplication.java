package cn.studyjams.s2.sj0131.common;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import cn.studyjams.s2.sj0131.common.di.component.AppComponent;
import cn.studyjams.s2.sj0131.common.di.component.DaggerAppComponent;
import cn.studyjams.s2.sj0131.common.di.module.AppModule;
import cn.studyjams.s2.sj0131.common.di.module.NetModule;

/**
 * Created by hasee on 2017/5/6.
 */

public class CommonApplication extends Application {

    private static CommonApplication mInstance;
    private AppComponent mAppComponent;

    public static CommonApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();

    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例,在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getmAppComponent() {
        return mAppComponent;
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
