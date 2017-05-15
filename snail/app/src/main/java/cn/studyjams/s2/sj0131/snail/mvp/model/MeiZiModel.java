package cn.studyjams.s2.sj0131.snail.mvp.model;

import cn.studyjams.s2.sj0131.common.mvp.BaseModel;
import cn.studyjams.s2.sj0131.snail.api.CommonService;
import cn.studyjams.s2.sj0131.snail.mvp.contract.MeiZiContract;

/**
 * Created by hasee on 2017/5/11.
 */

public class MeiZiModel extends BaseModel implements MeiZiContract.Model {


    private CommonService mCommonService;

    public MeiZiModel(CommonService commonService) {
        mCommonService = commonService;
    }


    @Override
    public void onDestroy() {

    }
}
