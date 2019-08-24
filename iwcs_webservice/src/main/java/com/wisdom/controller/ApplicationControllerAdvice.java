package com.wisdom.controller;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.domain.upstream.mes.MesResult.NG;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);


    @ExceptionHandler(BusinessException.class)
    public Result robotExceptionDeal(BusinessException re) {
        if (logger.isErrorEnabled()) {
            logger.error("业务错误信息:{}", re);
        }
        Result response = new Result();
        response.setReturnCode(400);
        response.setReturnMsg(re.getMsg());
        response.setReturnData(new HashMap<>());
        if (logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return response;

    }

    @ExceptionHandler(MesBusinessException.class)
    public MesResult runtimeExceptionDeal(MesBusinessException re) {
        if (logger.isErrorEnabled()) {
            logger.error("其他运行时错误信息:{}", re.getMessage());
        }
        MesResult response = new MesResult();
        response.setCode(NG);
        response.setMessage(re.getMsg());
        if (StringUtils.isNotBlank(re.getReqCode())) {
            response.setReqCode(re.getReqCode());
        } else {
            //数据库查询出reqCode
        }
        if (logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result argumentValidHandler(MethodArgumentNotValidException re) {
        if (logger.isErrorEnabled()) {
            logger.error("业务错误信息:{}", re);
        }

        Result response = new Result();
        response.setReturnCode(403);

        BindingResult result = re.getBindingResult();
        List<String> errList;

        if (result != null) {
            errList = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
            response.setReturnMsg(String.join("\n", errList));
        } else {
            response.setReturnMsg(new String("argument error"));
        }

        response.setReturnData(new HashMap<>());
        if (logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return response;

    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionDeal(RuntimeException re) {
        if (logger.isErrorEnabled()) {
            logger.error("其他运行时错误信息:{}", re);
        }
        Result response = new Result();
        response.setReturnCode(400);
        response.setReturnMsg("系统运行错误,请联系管理员");
        response.setReturnData(new HashMap<>());
        if (logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return response;
    }
}
