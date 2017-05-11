package cn.studyjams.s2.sj0131.snail.mvp.ui;

import android.content.Intent;
import android.view.View;

import cn.studyjams.s2.sj0131.common.base.BaseFragment;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.presenter.MeiZiPresenter;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiFragment extends BaseFragment<MeiZiPresenter> implements MeiZiContract.View{


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
