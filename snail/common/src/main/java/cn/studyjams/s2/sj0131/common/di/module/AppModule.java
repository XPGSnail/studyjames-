package cn.studyjams.s2.sj0131.common.di.module;

import javax.inject.Singleton;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hasee on 2017/5/15.
 */
@Module
public class AppModule {

    private CommonApplication mCommonApplication;

    public AppModule(CommonApplication commonApplication) {
        mCommonApplication = commonApplication;
    }

    @Provides
    @Singleton
    CommonApplication provideApplication() {
        return mCommonApplication;
    }

}
