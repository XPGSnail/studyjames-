package cn.studyjams.s2.sj0131.common.api.interceptor;

/**
 * Created by hasee on 2017/4/17.
 */

import java.io.IOException;
import java.net.UnknownHostException;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.common.utils.NetworkUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存控制
 */
public class CacheControlInterceptor implements Interceptor {

    public CacheControlInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException, UnknownHostException {
        Request request = chain.request();
//            if (!NetworkUtil.isConnected(MyApplication.getInstance())) {
//                Request builder = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE).build();
//            }

        Response response = chain.proceed(request);

        if (NetworkUtil.isConnected(CommonApplication.getInstance())) {
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