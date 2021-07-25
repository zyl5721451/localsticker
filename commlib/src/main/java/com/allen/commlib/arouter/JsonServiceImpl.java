package com.allen.commlib.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.lang.reflect.Type;
@Route(path = "/arouterservice/json")
public class JsonServiceImpl implements SerializationService {
    Gson gson = new Gson();
    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
//        return JSON.parseObject(input, clazz);
        return gson.fromJson(input,clazz);
    }

    @Override
    public String object2Json(Object instance) {
//        return JSON.toJSONString(instance);
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
//        return JSON.parseObject(input, clazz);
        return gson.fromJson(input,clazz);
    }

    @Override
    public void init(Context context) {

    }
}
