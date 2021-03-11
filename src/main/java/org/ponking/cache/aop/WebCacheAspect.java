package org.ponking.cache.aop;

import cn.hutool.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.ponking.cache.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Author ponking
 * @Date 2021/3/11 16:33
 */
@Aspect
@Component
public class WebCacheAspect {

    private Logger logger = LoggerFactory.getLogger(WebCacheAspect.class);

    @Autowired
    private Cache cache;

    /**
     *  *：匹配所有字符
     *  ..：一般用于匹配多个包，多个参数
     *  +：表示类及其子类
     *  运算符有：&&,||,!
     *
     *
     * execution - 匹配方法执行的连接点，这是你将会用到的Spring的最主要的切入点指示符。
     *
     * within - 限定匹配特定类型的连接点（在使用Spring AOP的时候，在匹配的类型中定义的方法的执行）。
     *
     * this - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中bean reference（Spring AOP 代理）是指定类型的实例。
     *
     * target - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中目标对象（被代理的应用对象）是指定类型的实例。
     *
     * args - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中参数是指定类型的实例。
     *
     * @target - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中正执行对象的类持有指定类型的注解。
     *
     * @args - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中实际传入参数的运行时类型持有指定类型的注解。
     *
     * @within - 限定匹配特定的连接点，其中连接点所在类型已指定注解（在使用Spring AOP的时候，所执行的方法所在类型已指定注解）。
     *
     * @annotation - 限定匹配特定的连接点（使用Spring AOP的时候方法的执行），其中连接点的主题持有指定的注解。
     */

    @Pointcut("execution(public * org.ponking.cache.controller..*.*(..))") //execution([可见性]返回类型[声明类型].方法名(参数)[异常])
    public void doWeb() {
    }

    @Around("doWeb()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object key = getKey(methodName, args);
        if (cache.containsKey(key) || key != null) {
            logger.info("Get Data from Cache");
            if (methodName.equals("update")) {
                Object user = joinPoint.proceed();
                cache.put(key, user);
                return user;
            } else if (methodName.equals("delete")) {
                cache.remove(key);
                return joinPoint.proceed();
            } else {
                return cache.get(key);
            }
        } else {
            logger.info("Get Data from DB");
            Object user = joinPoint.proceed();
            if (methodName != "insert") {
                cache.put(key, user);
            }
            return user;
        }
    }

    public Object getKey(String method, Object[] args) {
        if ("update".equals(method) || "delete".equals(method)) {
            User user = (User) args[0];
            String key = "user" + ":" + user.getId();
            return key;
        } else if ("getOneById".equals(method)) {
            return "user" + ":" + args[0];
        }
        return null;
    }

}
