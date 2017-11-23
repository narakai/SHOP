package wiki.scene.shop.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import wiki.scene.shop.http.api.ApiUtil;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.callback.JsonCallback;

/**
 * 上传页面信息
 * Created by scene on 2017/11/23.
 */

public class UpdatePageUtils {
    public static void updatePagePosition(int positionId,int dataId){
        HttpParams params=new HttpParams();
        params.put("data_id",dataId);
        params.put("position_id",positionId);
        OkGo.<LzyResponse<String>>get(ApiUtil.API_PRE+ApiUtil.UPDATE_PAGE)
                .tag(ApiUtil.UPDATE_PAGE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {

                    }
                });
    }
}
