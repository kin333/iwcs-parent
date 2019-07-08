package com.wisdom.iwcs.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(NetWorkUtil.class);

    public static <T> String transferContinueTask(T t, String url) {
        logger.debug("开始发送: {}", url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<T> requestEntity = new HttpEntity<>(t, httpHeaders);
        logger.info("发送hik，信息体：" + t.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        List<String> val = resp.getHeaders().get("Set-Cookie");
        logger.debug("发送成功: {}, Set-Cookie: {}", url, val);

        return resp.getBody();
    }
}
