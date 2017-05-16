package cn.studyjams.s2.sj0131.snail.mvp.model;

import javax.inject.Inject;

import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.common.mvp.BaseModel;
import cn.studyjams.s2.sj0131.snail.api.CommonService;
import cn.studyjams.s2.sj0131.snail.entity.MeiZi;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;
import io.reactivex.Observable;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiModel extends BaseModel implements MeiZiContract.Model {


    private CommonService mCommonService;

    @Inject
    public MeiZiModel(CommonService commonService) {
        mCommonService = commonService;
    }


    @Override
    public Observable<HttpResult<MeiZi>> getMeiziObservable(int page) {
        return mCommonService.getMeizi(page);
    }
}
