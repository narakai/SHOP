package wiki.scene.shop.http.callback;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;
import wiki.scene.shop.http.base.LzyResponse;
import wiki.scene.shop.http.base.SimpleResponse;

/**
 * Case By:
 * package:wiki.scene.shop.http.callback
 * Author：scene on 2017/6/27 11:38
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("没有填写泛型参数");
        }
        //得到第二层泛型的真实数据
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != LzyResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                SimpleResponse simpleResponse = gson.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                return (T) simpleResponse.toBaseResponse();
            } else {
                LzyResponse baseResponse = gson.fromJson(jsonReader, type);
                response.close();
                int code = baseResponse.code;
                if (code == 200) {
                    //成功
                    return (T) baseResponse;
                } else if (code == 1) {
                    throw new IllegalStateException(baseResponse.message);
                } else {
                    throw new IllegalStateException(baseResponse.message);
                }

            }
        }

    }
}
