package com.xxy.springboot.incepter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxy
 * @version V1.0
 * @Description: 统计各个方法的执行时间
 */
//@Aspect
@Component
public class ServiceInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);

    ThreadLocal<Map<String, Long>> methodInvokedCount = new ThreadLocal<>();
    ThreadLocal<Map<String, Long>> methodExecuteTime = new ThreadLocal<>();

    @Pointcut("execution( * com.xxy.springboot.rest.*.*(..))")
    public void aopPointCut(){}

    /**
     * Aop：环绕通知 切整个包下面的所有涉及到调用的方法的信息
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aopPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //初始化 一次
        if (methodInvokedCount.get() == null) {
            methodInvokedCount.set(new HashMap<>());
        }
        if (methodExecuteTime.get() == null) {
            methodExecuteTime.set(new HashMap<>());
        }
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            //如果切到了没有返回类型的void方法则直接返回
            if (result == null) {
                return null;
            }
            long end = System.currentTimeMillis();
            logger.info("===================================================================");
            String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String MethodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String params = "";
            int argsSize = args.length;
            StringBuffer argsTypes = new StringBuffer();
            String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
            String returnType = joinPoint.getSignature().toString().split(" ")[0];
            logger.info("类/接口:" + tragetClassName + "(" + typeStr + ")");
            logger.info("方法:" + MethodName);
            logger.info("参数个数:" + argsSize);
            logger.info("请求参数:" + params);
            logger.info("返回类型:" + returnType);
            if (argsSize > 0) {
                // 拿到参数的类型
                for (Object object : args) {
                    argsTypes.append(object.getClass().getTypeName().toString()).append(" ");
                }
                logger.info("参数类型：" + argsTypes);
            }
            Long total = end - start;
            logger.info("耗时: " + total + " ms!");
            if (methodInvokedCount.get().containsKey(MethodName)) {
                Long count = methodInvokedCount.get().get(MethodName);
                methodInvokedCount.get().remove(MethodName);//先移除，在增加
                methodInvokedCount.get().put(MethodName, count + 1);
                count = methodExecuteTime.get().get(MethodName);
                methodExecuteTime.get().remove(MethodName);
                methodExecuteTime.get().put(MethodName, count + total);
            } else {
                methodInvokedCount.get().put(MethodName, 1L);
                methodExecuteTime.get().put(MethodName, total);
            }
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.info("====around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
                    + e.getMessage());
            throw e;
        }
    }
}
