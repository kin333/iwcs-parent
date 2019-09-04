package com.wisdom.config;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.log.InterfaceLog;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.log.InterfaceLogMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by cecilia.yang
 * <p>
 * This class is used for Spring AOP.
 */

@Aspect
@Component
@Order(1)
public class AspectInterfaceLogConfiguration {

    Logger logger = LoggerFactory.getLogger(AspectConfiguration.class);
    @Autowired
    private InterfaceLogMapper interfaceLogMapper;


    /**
     * 定义切入点，可以是规则表达式，也可以是package下的所有函数，也可以是一个注解等
     * public * com.wisdom.iwcs.service.TPSService..*.*(..)
     */
    @Pointcut("execution(public * com.wisdom.controller..*.*(..))")
    public void cutController() {
    }

    /**
     * 切面记录日志
     *
     * @param pjp
     * @param systemInterfaceLog
     * @return
     * @throws Throwable
     */
    @Around("cutController() && @annotation(systemInterfaceLog)")
    public Object doAround(ProceedingJoinPoint pjp, SystemInterfaceLog systemInterfaceLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //时间转换
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //打印到控制台
        String requestUniqueCode = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        logger.info("请求地址 ，{}，{} ", requestUniqueCode, request.getRequestURL().toString());
        logger.info("接收手持请求时间,{},{} ", requestUniqueCode, format0.format(now));
        logger.info("请求方法,{},{} ", requestUniqueCode, systemInterfaceLog.methodName());
        logger.info("IP地址 ,{},{} ", requestUniqueCode, request.getRemoteAddr());
        logger.info("请求函数,{},{} ", requestUniqueCode, pjp.getSignature().getName());
        logger.info("入参 ,{},{}", requestUniqueCode, net.sf.json.JSONArray.fromObject(pjp.getArgs()));
        InterfaceLog interfaceLog = new InterfaceLog();
        interfaceLog.setReceiveTime(now);
        interfaceLog.setSrcClientCode(systemInterfaceLog.methodThansfer());
        interfaceLog.setInterfaceCode(systemInterfaceLog.methodCode());
        interfaceLog.setInterfaceName(systemInterfaceLog.methodName());
        interfaceLog.setInParam(net.sf.json.JSONArray.fromObject(pjp.getArgs()).toString());
        interfaceLog.setSevThreadId(String.valueOf(Thread.currentThread().getId()));
        interfaceLog.setSevThreadName(Thread.currentThread().getName());
        interfaceLog.setReqIp(request.getRemoteAddr());
        interfaceLog.setReqCode(requestUniqueCode);

        //检查reqCode是否重复
        MesResult mesResult = thirdPartyReqCode(pjp, interfaceLog);
        if (MesResult.NG.equals(mesResult.getCode())) {
            return mesResult;
        }

        Object ob = new Object();
        try {
            // ob 为方法的返回值
            ob = pjp.proceed();
            logger.info("出参 ,{},{}", requestUniqueCode, net.sf.json.JSONArray.fromObject(ob));
            interfaceLog.setOutParam(net.sf.json.JSONObject.fromObject(ob).toString());
            interfaceLog.setEndTime(new Date());
            Long successTimeCost = System.currentTimeMillis() - startTime;
            logger.info("成功耗时 ,{},{}", requestUniqueCode, successTimeCost);
            interfaceLog.setTimeCost(successTimeCost.toString());
            interfaceLog.setCreatedTime(new Date());
            interfaceLogMapper.insertSelective(interfaceLog);
        } catch (Exception e) {
            logger.info("异常信息，{}，{}", requestUniqueCode, e.getMessage());
            interfaceLog.setOutParam("unexpectedError:" + e.getMessage());
            interfaceLog.setEndTime(new Date());
            Long exceptionTimeCost = System.currentTimeMillis() - startTime;
            logger.info("异常耗时 ,{},{}", requestUniqueCode, exceptionTimeCost);
            interfaceLog.setTimeCost(exceptionTimeCost.toString());
            interfaceLogMapper.insertSelective(interfaceLog);
            throw e;
        }
        return ob;
    }

    /**
     * 使用第三方的reqCode
     * 如果第三方传入reqCode,则使用第三方的reqCode
     * @param pjp
     * @param interfaceLog
     * @return
     */
    private MesResult thirdPartyReqCode(ProceedingJoinPoint pjp, InterfaceLog interfaceLog) {
        JSONArray jsonArray = JSONArray.fromObject(pjp.getArgs());
        if (jsonArray.size() > 0) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String reqcode = jsonObject.getString("reqcode");
                if (StringUtils.isNotEmpty(reqcode)) {
                    int count = interfaceLogMapper.selectCountByReqCode(reqcode);
                    if (count > 0) {
                        return new MesResult(MesResult.NG, "请求编码已存在", reqcode);
                    }
//                    interfaceLog.setReqCode(reqcode);
                }
            } catch (JSONException e) {
                //使用自动生成的reqCode
            }
        }
        return new MesResult();
    }
}
