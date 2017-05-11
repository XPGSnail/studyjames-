
package cn.studyjams.s2.sj0131.snail.api;

/**
 * Created by panda.guo on 2016/11/26.
 */
public class HttpUtil {

    private CommonService mCommonService;

    private static class DefaultHolder {
        private static HttpUtil INSTANCE = new HttpUtil();

    }


    private HttpUtil() {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class);
    }

    public static HttpUtil getInstance() {
        return DefaultHolder.INSTANCE;
    }



}
