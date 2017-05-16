package cn.studyjams.s2.sj0131.snail.mvp.contract;

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

    }

    interface Model extends IModel {

        Observable<HttpResult<MeiZi>> getMeiziObservable(int page);

    }
}
