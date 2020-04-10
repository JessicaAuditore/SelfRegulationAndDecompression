package com.limitofsoul.utils;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

public class JsonUtil {

    private static Gson gson = new Gson();

    public static Gson getGson() {
        Log.i("gson1", gson.toString());
        return gson;
    }

    public static String objectToJson(Object obj) {
        return getGson().toJson(obj);

    }

    public static String listToJson(List list) {
        return getGson().toJson(list);

    }

    public static Object jsonToObject(String json, Class clazz) {
        Log.i("json", json);
        return getGson().fromJson(json, clazz);
    }

}
