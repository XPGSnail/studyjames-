package cn.studyjams.s2.sj0131.snail.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import cn.studyjams.s2.sj0131.Constants;
import cn.studyjams.s2.sj0131.common.base.BaseFragment;
import cn.studyjams.s2.sj0131.common.utils.Logger;
import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerMeiZiComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.MeiZiModule;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.presenter.MeiZiPresenter;
import cn.studyjams.s2.sj0131.snail.mvp.ui.activity.MainActivity;
import cn.studyjams.s2.sj0131.snail.mvp.ui.adapter.MeiZiAdapter;
import cn.studyjams.s2.sj0131.snail.widget.DividerDecoration;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiFragment extends BaseFragment<MeiZiPresenter> implements MeiZiContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<MeiZi> mDatas;
    private MeiZiAdapter mMeiZiAdapter;
    private int mPage = 1;
    private static final int MAX_PAGE = 10;

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
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        return view;
    }

    @Override
    protected void initData() {
        initRecyclerView();
        mPresenter.getMeiZiDatas(mPage, Constants.FLAG_INIT);
        initListener();
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mMeiZiAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public void initDatas(ArrayList<MeiZi> datas) {
        mMeiZiAdapter.setNewData(datas);
    }

    private void initRecyclerView() {
        mDatas = new ArrayList<>();
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        DividerDecoration decoration = new DividerDecoration(10);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mMeiZiAdapter = new MeiZiAdapter(mDatas);
        mRecyclerView.setAdapter(mMeiZiAdapter);
//        mMeiZiAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void inflateDatas(ArrayList<MeiZi> datas) {
        mMeiZiAdapter.setNewData(datas);
    }

    @Override
    public void addDatas(ArrayList<MeiZi> datas) {
        mMeiZiAdapter.addData(datas);
    }

    @Override
    public void hideLoadingMore(boolean b) {
        if (b) {
            mMeiZiAdapter.loadMoreComplete();
        } else {
            mMeiZiAdapter.loadMoreFail();
        }
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void showLoadingMore() {
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
        mMeiZiAdapter.setEnableLoadMore(false);
    }

    @Override
    public void hideLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mMeiZiAdapter.setEnableLoadMore(true);
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

    @Override
    public void onRefresh() {
        mPage = 1;
        Logger.logPD("mpage" + mPage);
        mPresenter.getMeiZiDatas(mPage, Constants.FLAG_REFRESH);
    }

    @Override
    public void onLoadMoreRequested() {

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPage >= MAX_PAGE) {
                    mMeiZiAdapter.loadMoreEnd();
                } else {
                    ++mPage;
                    mPresenter.getMeiZiDatas(mPage, Constants.FLAG_LOAD_MORE);
                }
                Logger.logPD("mpage" + mPage);
            }
        }, 500);
    }

}
