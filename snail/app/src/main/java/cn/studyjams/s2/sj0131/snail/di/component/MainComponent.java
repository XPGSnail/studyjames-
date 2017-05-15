package cn.studyjams.s2.sj0131.snail.di.component;

import cn.studyjams.s2.sj0131.common.di.component.AppComponent;
import cn.studyjams.s2.sj0131.common.di.scope.ActivityScope;
import cn.studyjams.s2.sj0131.snail.di.module.ServiceModule;
import cn.studyjams.s2.sj0131.snail.mvp.ui.MainActivity;
import dagger.Component;

/**
 * Created by hasee on 2017/5/15.
 */
@ActivityScope
@Component(modules = {ServiceModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
