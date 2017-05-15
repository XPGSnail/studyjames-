package cn.studyjams.s2.sj0131.snail.di.module;

import javax.inject.Singleton;

import cn.studyjams.s2.sj0131.snail.api.CommonService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by hasee on 2017/5/15.
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonService.class);
    }
}
