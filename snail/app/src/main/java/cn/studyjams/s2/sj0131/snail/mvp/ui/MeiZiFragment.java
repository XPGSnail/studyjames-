package cn.studyjams.s2.sj0131.snail.mvp.ui;

import android.content.Intent;
import android.view.View;

import cn.studyjams.s2.sj0131.common.base.BaseFragment;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerMeiZiComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.MeiZiModule;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.presenter.MeiZiPresenter;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiFragment extends BaseFragment<MeiZiPresenter> implements MeiZiContract.View {


    @Override
    protected void setComponent() {
        MainActivity activity = (MainActivity) getActivity();
        MainComponent mainComponent = activity.getMainComponent();
        DaggerMeiZiComponent.builder()
                .mainComponent(mainComponent)
                .meiZiModule(new MeiZiModule(this))
                .build().inject(this);
    }

    @Override
    protected MeiZiPresenter setPresenter() {
        return null;
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
