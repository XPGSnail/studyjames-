package cn.studyjams.s2.sj0131.common.di.component;

import cn.studyjams.s2.sj0131.common.CommonApplication;
import cn.studyjams.s2.sj0131.common.di.module.AppModule;
import cn.studyjams.s2.sj0131.common.di.module.NetModule;
import dagger.Component;

/**
 * Created by hasee on 2017/5/15.
 */
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(CommonApplication application);

}
