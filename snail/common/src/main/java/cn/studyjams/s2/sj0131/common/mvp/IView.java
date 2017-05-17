package cn.studyjams.s2.sj0131.common.mvp;

import android.content.Intent;

/**
 * Created by hasee on 2017/4/17.
 */

public interface IView {

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 跳转activity
     */
    void launchActivity(Intent intent);
    /**
     * 杀死自己
     */
    void killMyself();


}
