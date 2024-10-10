package org.jeecg.modules.aspect;

import com.alibaba.fastjson.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jeecg.modules.annotation.WrapResponse;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WrapResponseAspect {

    // 使用自定义注解进行切面拦截
    @Around("@annotation(wrapResponse)")
    public Object wrapResponse(ProceedingJoinPoint joinPoint, WrapResponse wrapResponse) throws Throwable {
        // 执行目标方法
        Object result = joinPoint.proceed();

        // 创建统一的返回格式
        JSONObject root = new JSONObject();

        root.put("code", "0");
        root.put("msg", "success");
        root.put("desc", "success");
        root.put("data", result);

        return root;
    }

}
