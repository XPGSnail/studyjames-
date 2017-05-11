package cn.studyjams.s2.sj0131.snail.mvp.presenter;

import cn.studyjams.s2.sj0131.common.mvp.BasePresenter;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.AndroidModel;

/**
 * Created by hasee on 2017/5/11.
 */

public class AndroidPresenter extends BasePresenter<AndroidModel, AndroidContract.View> {

    public AndroidPresenter(AndroidModel model, AndroidContract.View view) {
        super(model, view);
    }

    public AndroidPresenter(AndroidContract.View rootView) {
        super(rootView);
    }

    public AndroidPresenter() {
    }


}
