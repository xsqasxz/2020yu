package com.small.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xueshiqi
 * @since 2020/4/30
 * 公用返回数据
 */
@Getter
@Setter
public class JsonResponse {
    private boolean success;
    private String ok;
    private String error;
    private Object data;

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public static JsonResponse ok(String ok){
        JsonResponse jsonResponse = new JsonResponse(true);
        jsonResponse.setOk(ok);
        return jsonResponse;
    }

    public static JsonResponse okData(String ok,Object data){
        JsonResponse jsonResponse = new JsonResponse(true);
        jsonResponse.setOk(ok);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    public static JsonResponse error(String error){
        JsonResponse jsonResponse = new JsonResponse(false);
        jsonResponse.setError(error);
        return jsonResponse;
    }
}
