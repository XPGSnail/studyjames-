package cn.studyjams.s2.sj0131.common.api.func;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created by panda.guo on 2016/11/26.
 */
public class StringFunc implements Function<ResponseBody, String> {
    @Override
    public String apply(ResponseBody responseBody) {
        String result = null;
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
