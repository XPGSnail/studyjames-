package cn.studyjams.s2.sj0131.common.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.common.api.interceptor.CacheControlInterceptor;
import cn.studyjams.s2.sj0131.common.api.interceptor.LoggingInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hasee on 2017/4/17.
 */
@Module
public class NetModule {
    private static final int DEFAULT_TIMEOUT = 10;

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkttpClient(Cache cache) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.cache(cache);//缓存大小10M
        //设置拦截器
        httpClientBuilder.addInterceptor(new LoggingInterceptor());
        httpClientBuilder.addNetworkInterceptor(new CacheControlInterceptor());
        httpClientBuilder.addInterceptor(new CacheControlInterceptor());
        return httpClientBuilder.build();
    }

    @Singleton
    @Provides
    Cache provideCache(CommonApplication myApplication) {
        //设置缓存
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File cacheDir = myApplication.getCacheDir();
        File httpCacheDirectory = new File(cacheDir, "OkHttpCache");
        return new Cache(httpCacheDirectory, cacheSize);
    }

    protected Retrofit provideGankRetrofit(OkHttpClient okHttpClient, Gson gson, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
