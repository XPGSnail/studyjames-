package cn.studyjams.s2.sj0131.snail.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import cn.studyjams.s2.sj0131.Constants;
import cn.studyjams.s2.sj0131.MyApplication;
import cn.studyjams.s2.sj0131.common.utils.Logger;
import cn.studyjams.s2.sj0131.common.utils.NetworkUtil;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by panda.guo on 2016/11/26.
 */
public class ServiceFactory {

    private final Gson mGsonDateFormat;

    private ServiceFactory() {
        mGsonDateFormat = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

    private static class SingletonHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * create a service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass) {
//        if (mRetrofit == null) {
//            synchronized (ServiceFactory.class) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_ROOT_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
//                mRetrofit = retrofit.create(serviceClass);
//            }
//        }

        return retrofit.create(serviceClass);
    }

    Object mRetrofit;

    /**
     * create a service
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public <S> S createService(Class<S> serviceClass, String url) {
        if (mRetrofit == null) {
            synchronized (ServiceFactory.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .client(getOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                mRetrofit = retrofit.create(serviceClass);
            }
        }

        return (S) mRetrofit;
    }

    private final static long DEFAULT_TIMEOUT = 10;

    private OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存
        MyApplication instance = MyApplication.getInstance();
        File cacheDir = instance.getCacheDir();
        File httpCacheDirectory = new File(cacheDir, "OkHttpCache");
        httpClientBuilder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));//缓存大小10M
        //设置拦截器
        httpClientBuilder.addInterceptor(new LoggingInterceptor());
        httpClientBuilder.addNetworkInterceptor(new CacheControlInterceptor());
        httpClientBuilder.addInterceptor(new CacheControlInterceptor());
//        if (Constants.Debugable) {
//            httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
//        }
        return httpClientBuilder.build();
    }

    /**
     * 缓存控制
     */
    class CacheControlInterceptor implements Interceptor {

        public CacheControlInterceptor() {
        }

        @Override
        public Response intercept(Chain chain) throws IOException, UnknownHostException {
            Request request = chain.request();


            Response response = chain.proceed(request);

            if (NetworkUtil.isConnected(MyApplication.getInstance())) {
                int maxAge = 0; // read from network
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 1; // tolerate 1-hour stale
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    }

    /**
     * 使用代理
     */
    class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "TokenRequest-Agent";
        private final String userAgentHeaderValue;

        public UserAgentInterceptor(String userAgentHeaderValue) {
            if (TextUtils.isEmpty(userAgentHeaderValue)) {
                throw new NullPointerException("userAgentHeaderValue = null");
            } else {
                this.userAgentHeaderValue = userAgentHeaderValue;
            }
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();
            final Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader(USER_AGENT_HEADER_NAME)
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

    /**
     * 打印log
     */
    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException, UnknownHostException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger.i(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Logger.i(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }


}
