package cn.studyjams.s2.sj0131.snail.mvp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import cn.studyjams.s2.sj0131.Constants;
import cn.studyjams.s2.sj0131.common.base.BaseFragment;
import cn.studyjams.s2.sj0131.common.utils.Logger;
import cn.studyjams.s2.sj0131.snail.R;
import cn.studyjams.s2.sj0131.snail.di.component.DaggerAndroidComponent;
import cn.studyjams.s2.sj0131.snail.di.component.MainComponent;
import cn.studyjams.s2.sj0131.snail.di.module.AndroidModule;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import cn.studyjams.s2.sj0131.snail.mvp.presenter.AndroidPresenter;
import cn.studyjams.s2.sj0131.snail.mvp.ui.activity.MainActivity;
import cn.studyjams.s2.sj0131.snail.mvp.ui.adapter.AndroidAdapter;
import cn.studyjams.s2.sj0131.snail.widget.DividerDecoration;

/**
 * Created by hasee on 2017/5/11.
 */

public class AndroidFragment extends BaseFragment<AndroidPresenter> implements AndroidContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    @Inject
    AndroidPresenter mPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<Android> mDatas;
    private AndroidAdapter mAdapter;
    private int mPage = 1;
    private static final int MAX_PAGE = 10;


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
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        return view;
    }

    @Override
    protected void initData() {
        initRecyclerView();
        mPresenter.getAndroidDatas(mPage, Constants.FLAG_REFRESH);
        initListener();
    }

    private void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    private void initRecyclerView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink, R.color.blue_grey);
        mDatas = new ArrayList<>();
        mAdapter = new AndroidAdapter(mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerDecoration dividerItemDecoration = new DividerDecoration(10);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void inflateDatas(ArrayList<Android> datas) {
        mAdapter.setNewData(datas);
        mAdapter.disableLoadMoreIfNotFullPage(mRecyclerView);
    }

    @Override
    public void addDatas(ArrayList<Android> datas) {
        mAdapter.addData(datas);
    }

    @Override
    public void hideLoadingMore(boolean b) {
        if (b) {
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreFail();
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
        mAdapter.setEnableLoadMore(false);
    }

    @Override
    public void hideLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mAdapter.setEnableLoadMore(true);
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
        mPresenter.getAndroidDatas(mPage, Constants.FLAG_REFRESH);
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPage >= MAX_PAGE) {
                    mAdapter.loadMoreEnd();
                } else {
                    ++mPage;
                    mPresenter.getAndroidDatas(mPage, Constants.FLAG_LOAD_MORE);
                }
                Logger.logPD("mpage" + mPage);
            }
        }, 500);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Android android = mAdapter.getData().get(position);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(android.getUrl()));
        startActivity(intent);
    }
}
