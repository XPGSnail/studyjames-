package cn.studyjams.s2.sj0131.snail.di.component;

import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.snail.di.module.MeiZiModule;
import cn.studyjams.s2.sj0131.snail.mvp.ui.MeiZiFragment;
import dagger.Component;

/**
 * Created by hasee on 2017/5/15.
 */
@FragmentScope
@Component(modules = {MeiZiModule.class}, dependencies = {MainComponent.class})
public interface MeiZiComponent {

    void inject(MeiZiFragment meiZiFragment);
}
