package cn.studyjams.s2.sj0131.common.api.subscriber;


import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.common.utils.Logger;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by panda.guo on 2016/11/26.
 */
public abstract class HttpResultSubscriber<T> implements Observer<HttpResult<T>> {

    protected Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        addDisposable();
    }

    protected abstract void addDisposable();

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(this, e.getMessage());
        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof HttpException) {
            // ToastUtils.getInstance().showToast(e.getMessage());
        }
        onFailure(e);
    }

    @Override
    public void onNext(HttpResult<T> t) {
        if (!t.isError())
            onSuccess(t.getResults());
        else {
            onFailure(new Throwable("error=" + t.isError()));
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);


}
