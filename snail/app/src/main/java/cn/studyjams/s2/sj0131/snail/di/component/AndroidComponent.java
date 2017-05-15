package cn.studyjams.s2.sj0131.snail.di.component;

import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.snail.di.module.AndroidModule;
import cn.studyjams.s2.sj0131.snail.mvp.ui.AndroidFragment;
import dagger.Component;

/**
 * Created by hasee on 2017/5/15.
 */
@FragmentScope
@Component(modules = {AndroidModule.class},dependencies = {MainComponent.class})
public interface AndroidComponent {

    void inject(AndroidFragment androidFragment);
}
