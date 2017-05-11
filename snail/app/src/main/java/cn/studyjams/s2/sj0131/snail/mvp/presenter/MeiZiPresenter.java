package cn.studyjams.s2.sj0131.snail.mvp.presenter;

import cn.studyjams.s2.sj0131.common.mvp.BasePresenter;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import cn.studyjams.s2.sj0131.snail.mvp.model.MeiZiModel;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiPresenter extends BasePresenter<MeiZiModel, MeiZiContract.View> {

    public MeiZiPresenter(MeiZiModel model, MeiZiContract.View view) {
        super(model, view);
    }

    public MeiZiPresenter(MeiZiContract.View rootView) {
        super(rootView);
    }

    public MeiZiPresenter() {
    }


}
