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

        JSONObject jsonObject = new JSONObject();
        //获取所有的字段
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //获取标签
            ColumnName annotation = declaredField.getAnnotation(ColumnName.class);
            //获取字段的get方法
            String methodName = "get" + declaredField.getName().substring(0, 1).toUpperCase() + declaredField.getName().substring(1);
            //向模板中加入子任务信息
            try {
                Method declaredMethod = t.getClass().getDeclaredMethod(methodName);
                //获取字段值
                Object invoke = declaredMethod.invoke(t);
                jsonObject.accumulate(annotation.value(), invoke);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObject.toString();
    }

}
