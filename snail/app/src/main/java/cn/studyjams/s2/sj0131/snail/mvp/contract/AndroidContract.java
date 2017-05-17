package cn.studyjams.s2.sj0131.snail.mvp.contract;

import java.util.ArrayList;

import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.common.mvp.IModel;
import cn.studyjams.s2.sj0131.common.mvp.IView;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import io.reactivex.Observable;

/**
 * Created by hasee on 2017/5/11.
 */

public interface AndroidContract {

    interface View extends IView {

        void showLoadingMore();


        void inflateDatas(ArrayList<Android> datas);

        void hideLoadingMore(boolean b);

        void addDatas(ArrayList<Android> datas);
    }

    interface Model extends IModel {

        Observable<HttpResult<ArrayList<Android>>> getAndroidObservable(int page);

    }
}
