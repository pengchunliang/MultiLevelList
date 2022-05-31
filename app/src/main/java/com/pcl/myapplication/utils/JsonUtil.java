package com.pcl.myapplication.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by pcl on 2019-10-18
 */
public class JsonUtil {

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text,clazz);
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

}
