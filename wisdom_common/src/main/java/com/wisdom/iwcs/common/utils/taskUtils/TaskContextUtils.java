package com.wisdom.iwcs.common.utils.taskUtils;

import com.wisdom.base.annotation.ColumnName;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * task_context表中的context类与DTO转换相关的工具
 * @author han
 */
public class TaskContextUtils {

    /**
     * 将context中的json转换为对象
     * @param jsonStr
     * @param tClass 返回类型的class
     * @return
     */
    public static <T> T  jsonToObject(String jsonStr, Class<T> tClass) {
        T result = null;
        try {
            result = tClass.newInstance();
            JSONObject jsonObject = new JSONObject(jsonStr);
            //获取DTO所有属性
            Field[] declaredFields = tClass.getDeclaredFields();
            //查询每个属性的ColumnName的key值,根据key值向对象中插入值
            for (Field declaredField : declaredFields) {
                ColumnName annotation = declaredField.getAnnotation(ColumnName.class);
                if (annotation == null) {continue;}
                if (!jsonObject.has(annotation.value())) {continue;}
                Object value = jsonObject.get(annotation.value());
                if (value == null || "null".equals(value.toString())) {continue;}
                //生成字段对应的set方法名
                String name = declaredField.getName();
                String setName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                //调用set方法
                Method method = tClass.getMethod(setName, declaredField.getType());
                method.invoke(result, value);
            }
        } catch (JSONException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 将对象转换为context中的json
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T t) {
        String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(t);

        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            ColumnName annotation = declaredField.getAnnotation(ColumnName.class);
            if (!declaredField.getName().equals(annotation.value())) {
                jsonString = jsonString.replace(declaredField.getName(), annotation.value());
            }
        }

        return jsonString;
    }

}
