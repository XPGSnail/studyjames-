package cn.studyjams.s2.sj0131.snail.api;

import java.util.ArrayList;

import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by panda.guo on 2016/11/27.
 */
public interface CommonService {

    @GET("data/福利/20/{page}")
    Observable<HttpResult<ArrayList<MeiZi>>> getMeizi(@Path("page") int page);


    @GET("data/Android/10/{page}")
    Observable<HttpResult<ArrayList<Android>>> getAndroid(@Path("page") int page);
}
