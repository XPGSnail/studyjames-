package cn.studyjams.s2.sj0131.snail.api;

import com.gservfocus.fsm.api.HttpResult;
import com.gservfocus.fsm.mvp.entity.ConfigResult;
import com.gservfocus.fsm.mvp.entity.request.CommonRequest;
import com.gservfocus.fsm.mvp.entity.request.CreateServiceRequest;
import com.gservfocus.fsm.mvp.entity.request.EngineerListRequest;
import com.gservfocus.fsm.mvp.entity.request.FaultDescChild;
import com.gservfocus.fsm.mvp.entity.request.FaultDescParent;
import com.gservfocus.fsm.mvp.entity.request.JobOrderRequest;
import com.gservfocus.fsm.mvp.entity.request.KnowlegeRequest;
import com.gservfocus.fsm.mvp.entity.request.MaintainDispatchRequest;
import com.gservfocus.fsm.mvp.entity.request.MaintainOrderListRequest;
import com.gservfocus.fsm.mvp.entity.request.MaintainOrderRefuseRequest;
import com.gservfocus.fsm.mvp.entity.request.OrderDetailRequest;
import com.gservfocus.fsm.mvp.entity.request.OrderDispatchRequest;
import com.gservfocus.fsm.mvp.entity.request.OrderOperateRequest;
import com.gservfocus.fsm.mvp.entity.request.OrdersResquest;
import com.gservfocus.fsm.mvp.entity.request.PwsdNormalRequest;
import com.gservfocus.fsm.mvp.entity.request.SubmitMaintainAssetOrderRequest;
import com.gservfocus.fsm.mvp.entity.request.SubmitMaintainNetOrderRequest;
import com.gservfocus.fsm.mvp.entity.request.SubmitServiceRequest;
import com.gservfocus.fsm.mvp.entity.request.SuggestRequest;
import com.gservfocus.fsm.mvp.entity.request.TokenRequest;
import com.gservfocus.fsm.mvp.entity.request.UpdatePicRequest;
import com.gservfocus.fsm.mvp.entity.request.UserInfo;
import com.gservfocus.fsm.mvp.entity.request.WXFileRequest;
import com.gservfocus.fsm.mvp.entity.response.AccessResult;
import com.gservfocus.fsm.mvp.entity.response.CodeCheckResult;
import com.gservfocus.fsm.mvp.entity.response.ContactResult;
import com.gservfocus.fsm.mvp.entity.response.CreateOrderResult;
import com.gservfocus.fsm.mvp.entity.response.CustomNetInfoResult;
import com.gservfocus.fsm.mvp.entity.response.EngineerListResult;
import com.gservfocus.fsm.mvp.entity.response.EquipHistoryResult;
import com.gservfocus.fsm.mvp.entity.response.EquipInfoResult;
import com.gservfocus.fsm.mvp.entity.response.FaultAppearanceReuslt;
import com.gservfocus.fsm.mvp.entity.response.FaultCauseMeasureResult;
import com.gservfocus.fsm.mvp.entity.response.GeoGraphiesResult;
import com.gservfocus.fsm.mvp.entity.response.JobOrderInfoResult;
import com.gservfocus.fsm.mvp.entity.response.KnowlegeResult;
import com.gservfocus.fsm.mvp.entity.response.LeftoverProblemResult;
import com.gservfocus.fsm.mvp.entity.response.LoginResult;
import com.gservfocus.fsm.mvp.entity.response.MaintainOrderInfoResult;
import com.gservfocus.fsm.mvp.entity.response.MaintainOrderListResult;
import com.gservfocus.fsm.mvp.entity.response.MatainOrderStubmitResponse;
import com.gservfocus.fsm.mvp.entity.response.OfficeDetailResult;
import com.gservfocus.fsm.mvp.entity.response.OrderDetailResult;
import com.gservfocus.fsm.mvp.entity.response.OrderOperateResult;
import com.gservfocus.fsm.mvp.entity.response.OrdersResult;
import com.gservfocus.fsm.mvp.entity.response.PhotoResult;
import com.gservfocus.fsm.mvp.entity.response.PwsdCodeResult;
import com.gservfocus.fsm.mvp.entity.response.PwsdNormalResult;
import com.gservfocus.fsm.mvp.entity.response.PwsdPhoneResult;
import com.gservfocus.fsm.mvp.entity.response.RankListResult;
import com.gservfocus.fsm.mvp.entity.response.RankResult;
import com.gservfocus.fsm.mvp.entity.response.SearchOrderResult;
import com.gservfocus.fsm.mvp.entity.response.ServiceTypeResult;
import com.gservfocus.fsm.mvp.entity.response.SubmitOrderResult;
import com.gservfocus.fsm.mvp.entity.response.SuggestResult;
import com.gservfocus.fsm.mvp.entity.response.Token;
import com.gservfocus.fsm.mvp.entity.response.UpdatePicResult;
import com.gservfocus.fsm.mvp.entity.response.VisionInfo;
import com.gservfocus.fsm.mvp.entity.response.WeatherResult;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by panda.guo on 2016/11/27.
 */
public interface CommonService {

    //获取申请单
    @POST("api/services/app/order/GetJobOrderDetail")
    Observable<HttpResult<JobOrderInfoResult>> getJobOrderDetail(@Body JobOrderRequest request);

}
