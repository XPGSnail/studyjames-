package cn.studyjams.s2.sj0131.snail.mvp.presenter;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.studyjams.s2.sj0131.Constants;
import cn.studyjams.s2.sj0131.common.api.subscriber.HttpResultSubscriber;
import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.common.mvp.BasePresenter;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.AndroidModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hasee on 2017/5/11.
 */
@FragmentScope
public class AndroidPresenter extends BasePresenter<AndroidModel, AndroidContract.View> {

    @Inject
    public AndroidPresenter(AndroidModel model, AndroidContract.View view) {
        super(model, view);
    }

    public AndroidPresenter(AndroidContract.View rootView) {
        super(rootView);
    }

    public AndroidPresenter() {
    }

    public void getAndroidDatas(int page, final int flagRefresh) {
        mModel.getAndroidObservable(page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        switch (flagRefresh) {
                            case Constants.FLAG_REFRESH:
                                mView.showLoading();
                                break;
                            case Constants.FLAG_LOAD_MORE:
                                mView.showLoadingMore();
                                break;
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<ArrayList<Android>>() {
                    @Override
                    protected void addDisposable() {
                        addSubscribe(mDisposable);
                    }

                    @Override
                    public void onSuccess(ArrayList<Android> datas) {
                        switch (flagRefresh) {
                            case Constants.FLAG_REFRESH:
                                mView.hideLoading();
                                mView.inflateDatas(datas);
                                break;
                            case Constants.FLAG_LOAD_MORE:
                                mView.hideLoadingMore(true);
                                mView.addDatas(datas);
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        switch (flagRefresh) {
                            case Constants.FLAG_REFRESH:
                                mView.hideLoading();
                                break;
                            case Constants.FLAG_LOAD_MORE:
                                mView.hideLoadingMore(false);
                                break;
                        }
                        e.printStackTrace();
                    }
                });

    }
}
