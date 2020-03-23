package com.suzhou.cabinet.interceptor;

import com.suzhou.cabinet.utils.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author leo
 * @since 2018/7/27 10:53
 */
@Aspect
@Configuration
@Slf4j
public class LogAspect {

    /**
     * 切入所有带有@ApiOperation注解的方法
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    void doController() {
    }

    @Around("doController()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String uri = request.getRequestURI();
        String method = request.getMethod();
        String params = getArg(joinPoint);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        if (result != null && result instanceof RestResult) {
            // 如果当前返回结果的数据项为空，且操作成功则自动填充空的数组
            RestResult restResult = ((RestResult) result);

        }


        // 如果为登陆接口，则请求用户置空
        String loginName = "suzhou";

        log.debug("\n请求用户:{} 请求IP:{} 请求地址:{} 请求方法:{} 请求费时:{}ms\n请求参数:{}\n请求结果:{}",
                loginName, getIpAddress(request), uri, method, endTime - startTime, params, toJSONString(result));


        return result;
    }

    private String getArg(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            List<Object> paramList = new ArrayList<>(Arrays.asList(args));
            Iterator iterator = paramList.iterator();
            while(iterator.hasNext()){
                Object object = iterator.next();
                if(object instanceof HttpServletRequest){
                    iterator.remove();
                }
            }
            return toJSONString(paramList.toArray());
        } catch (Exception e) {
            log.error("e",e);
        }
        return "";
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 ) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

