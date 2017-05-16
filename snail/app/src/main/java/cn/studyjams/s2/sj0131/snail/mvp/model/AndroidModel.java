package cn.studyjams.s2.sj0131.snail.mvp.model;

import javax.inject.Inject;

import cn.studyjams.s2.sj0131.common.api.HttpResult;
import cn.studyjams.s2.sj0131.common.mvp.BaseModel;
import cn.studyjams.s2.sj0131.snail.api.CommonService;
import cn.studyjams.s2.sj0131.snail.entity.Android;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;
import io.reactivex.Observable;

/**
 * Created by hasee on 2017/5/11.
 */

public class AndroidModel extends BaseModel implements AndroidContract.Model {


    private CommonService mCommonService;

    @Inject
    public AndroidModel(CommonService commonService) {
        mCommonService = commonService;
    }


    @Override
    public Observable<HttpResult<Android>> getAndroidObservable(int page) {
        return mCommonService.getAndroid(page);
    }
}
