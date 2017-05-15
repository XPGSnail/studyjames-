package cn.studyjams.s2.sj0131.snail.mvp.model;

import cn.studyjams.s2.sj0131.common.mvp.BaseModel;
import cn.studyjams.s2.sj0131.snail.api.CommonService;
import cn.studyjams.s2.sj0131.snail.mvp.contract.AndroidContract;

/**
 * Created by hasee on 2017/5/11.
 */

public class AndroidModel extends BaseModel implements AndroidContract.Model {


    private CommonService mCommonService;

    public AndroidModel(CommonService commonService) {
        mCommonService = commonService;
    }

    @Override
    public void onDestroy() {

    }
}
