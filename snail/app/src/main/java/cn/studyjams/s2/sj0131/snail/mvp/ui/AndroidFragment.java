package cn.studyjams.s2.sj0131.snail.mvp.ui;

import android.content.Intent;
import android.view.View;

import javax.inject.Inject;

import cn.studyjams.s2.sj0131.common.base.BaseFragment;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerAndroidComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.AndroidModule;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import cn.studyjams.s2.sj0131.snail.mvp.presenter.AndroidPresenter;

/**
 * Created by hasee on 2017/5/11.
 */

public class AndroidFragment extends BaseFragment<AndroidPresenter> implements AndroidContract.View{

    @Inject
    AndroidPresenter mPresenter;

    @Override
    protected void setComponent() {
        MainActivity activity = (MainActivity) getActivity();
        MainComponent mainComponent = activity.getMainComponent();
        DaggerAndroidComponent.builder()
                .mainComponent(mainComponent)
                .androidModule(new AndroidModule(this))
                .build().inject(this);
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }
}
