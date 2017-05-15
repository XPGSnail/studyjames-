package cn.studyjams.s2.sj0131.snail.di.module;

import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.MeiZiModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hasee on 2017/5/15.
 */
@Module
public class MeiZiModule {
    private MeiZiContract.View mView;

    public MeiZiModule(MeiZiContract.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    MeiZiContract.View provideView() {
        return mView;
    }

    @Provides
    @FragmentScope
    MeiZiContract.Model provideModel(MeiZiModel meiZiModel) {
        return meiZiModel;
    }


}
