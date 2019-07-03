package com.wisdom.iwcs.common.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 网络传输工具类
 */
public class NetWorkUtil {

    public static <T> String transferContinueTask(T t, String url) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");

        HttpEntity<T> requestEntity = new HttpEntity<>(t, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        List<String> val = resp.getHeaders().get("Set-Cookie");
        System.out.println(val);

        String body = resp.getBody();

        return body;
    }
}
