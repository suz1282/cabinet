package com.suzhou.cabinet.utils;

//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.http.HttpRequest;
import com.wpg.common.utils.JsonSerializeUtil;
import com.xiaoleilu.hutool.bean.BeanUtil;
import com.xiaoleilu.hutool.http.HttpRequest;

import java.util.Map;

public class HutoolHttpUtil {

    public static <T, V> T hutoolPost(V request, String url, Class<T> returnClass){
        String result = HttpRequest.post(url)
                .header("Content-Type","application/json")
                //.header("Authorization", "Y3JtOi9sb2dpblVzZXIvcm9vdC80YzA0ZjUyNy0xNmY5LTQwYmItOGQzOS1kM2ZkZTNhODEyMGQ=")
                //.timeout(20000)  ///超时，毫秒
                .body(JsonSerializeUtil.jsonSerializerNoType(request))
                .execute().body();
        return JsonSerializeUtil.jsonReSerializerNoType(result, returnClass);
    }

    public static <T> T hutoolGet(String url, Class<T> returnClass){
        String result = HttpRequest.get(url)
                .execute().body();
        return JsonSerializeUtil.jsonReSerializerNoType(result, returnClass);
    }

    public static <T> T hutoolGet(String url, Class<T> returnClass, Map<String, Object> requestParam){
        String result = HttpRequest.get(url)
                .form(requestParam)
                .execute().body();
        return JsonSerializeUtil.jsonReSerializerNoType(result, returnClass);
    }

    public static <T, V> T hutoolGet(String url, Class<T> returnClass, V requestParam){
        String result = HttpRequest.get(url)
                .form(BeanUtil.beanToMap(requestParam))
                .execute().body();
        return JsonSerializeUtil.jsonReSerializerNoType(result, returnClass);
    }
}
