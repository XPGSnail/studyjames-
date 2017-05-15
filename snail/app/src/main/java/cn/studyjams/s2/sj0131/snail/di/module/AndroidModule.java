package cn.studyjams.s2.sj0131.snail.di.module;

import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.AndroidModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by hasee on 2017/5/15.
 */
@Module
public class AndroidModule {

    private AndroidContract.View mView;

    public AndroidModule(AndroidContract.View view) {
        mView = view;
    }

    @Provides
    @FragmentScope
    AndroidContract.View provideView(){
        return mView;
    }

    @Provides
    @FragmentScope
    AndroidContract.Model provideModel(AndroidModel model){
        return model;
    }

}
