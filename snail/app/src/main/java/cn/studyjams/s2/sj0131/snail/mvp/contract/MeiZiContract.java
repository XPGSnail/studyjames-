package cn.studyjams.s2.sj0131.snail.mvp.contract;

import java.util.ArrayList;

import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.common.mvp.IModel;
import cn.studyjams.s2.sj0131.common.mvp.IView;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import io.reactivex.Observable;

/**
 * Created by hasee on 2017/5/11.
 */

public interface MeiZiContract {

    interface View extends IView {

        void inflateDatas(ArrayList<MeiZi> datas);

        void addDatas(ArrayList<MeiZi> datas);

        void hideLoadingMore(boolean b);

        void showLoadingMore();
    }

    interface Model extends IModel {

        Observable<HttpResult<ArrayList<MeiZi>>> getMeiziObservable(int page);

    }
}
