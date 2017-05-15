package cn.studyjams.s2.sj0131.common.api.interceptor;

/**
 * Created by hasee on 2017/4/17.
 */

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 使用代理
 */
public class UserAgentInterceptor implements Interceptor {
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