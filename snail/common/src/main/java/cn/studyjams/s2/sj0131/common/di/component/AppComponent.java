package cn.studyjams.s2.sj0131.common.di.component;

import com.google.gson.Gson;

import javax.inject.Singleton;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.common.di.module.AppModule;
import cn.studyjams.s2.sj0131.common.di.module.NetModule;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by hasee on 2017/5/15.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(CommonApplication application);

    //retrofit
    Retrofit retrofit();

    //application
    CommonApplication commonApplication();

    //gson
    Gson gson();

}
