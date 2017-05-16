package cn.studyjams.s2.sj0131.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.studyjams.s2.sj0131.common.mvp.BasePresenter;

/**
 * Created by panda.guo on 2016/11/27.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    private View mInitView;
    private Unbinder mUnbinder;
    @Inject
    protected P mPresenter;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInitView = initView(inflater,container,savedInstanceState);
        mUnbinder = ButterKnife.bind(this, mInitView);
        setComponent();
        return mInitView;
    }

    protected abstract void setComponent();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        if (useEventBus()) {//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册到事件主线
        }
        initData();
    }

    /**
     * 是否使用eventBus,默认为不使用(false)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();//解绑ButterKnife
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 初始化Fragment的根节点
     *
     * @return view
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化fragment的数据源
     */
    protected abstract void initData();

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传bundle,里面存一个what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事,和message同理
     * <p>
     * ps:使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在内部onActivityCreated中
     * 初始化就可以了
     *
     * 这里也可以写成泛型方法,在使用fragment时候再去指定泛型参数
     *
     * @param data 传入的数据源
     */
    public void setData(Object data) {

    }
}
