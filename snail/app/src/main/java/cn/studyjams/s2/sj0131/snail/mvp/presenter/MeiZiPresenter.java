package cn.studyjams.s2.sj0131.snail.mvp.presenter;

import android.graphics.Bitmap;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.studyjams.s2.sj0131.Constants;
import cn.studyjams.s2.sj0131.common.api.subscriber.HttpResultSubscriber;
import cn.studyjams.s2.sj0131.common.di.scope.FragmentScope;
import cn.studyjams.s2.sj0131.common.mvp.BasePresenter;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.MeiZiModel;
import cn.studyjams.s2.sj0131.snail.utils.ImageLoader;
import cn.studyjams.s2.sj0131.snail.utils.UiUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hasee on 2017/5/11.
 */
@FragmentScope
public class MeiZiPresenter extends BasePresenter<MeiZiModel, MeiZiContract.View> {

    @Inject
    public MeiZiPresenter(MeiZiModel model, MeiZiContract.View view) {
        super(model, view);
    }

    public MeiZiPresenter(MeiZiContract.View rootView) {
        super(rootView);
    }

    public MeiZiPresenter() {
    }


    public void getMeiZiDatas(int page, final int flagRefresh) {
        mModel.getMeiziObservable(page)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        switch (flagRefresh) {
                            case Constants.FLAG_INIT:
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
                .subscribe(new HttpResultSubscriber<ArrayList<MeiZi>>() {
                    @Override
                    protected void addDisposable() {
                        addSubscribe(mDisposable);
                    }

                    @Override
                    public void onSuccess(ArrayList<MeiZi> datas) {
                        dealData(datas, flagRefresh);

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

    private void dealData(final ArrayList<MeiZi> datas, final int flagRefresh) {
        Observable.fromIterable(datas)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<MeiZi>() {
                    @Override
                    public void accept(@NonNull MeiZi zi) throws Exception {
                        Bitmap bitmap = ImageLoader.loadImageBitmap(zi.getUrl(),
                                UiUtils.getContext());
                        if (bitmap != null) {
                            zi.setWidth(bitmap.getWidth());
                            zi.setHeight(bitmap.getHeight());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeiZi>() {
                    Disposable mDisposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(@NonNull MeiZi zi) {
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        switch (flagRefresh) {
                            case Constants.FLAG_REFRESH:
                                mView.hideLoading();
                                mView.initDatas(datas);
                                break;
                            case Constants.FLAG_INIT:
                                mView.hideLoading();
                                mView.inflateDatas(datas);
                                break;
                            case Constants.FLAG_LOAD_MORE:
                                mView.hideLoadingMore(true);
                                mView.addDatas(datas);
                                break;
                        }
                    }
                });
    }
}
