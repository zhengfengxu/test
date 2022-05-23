package com.rsa;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {
    //所有com.example.demo.controller下的方法
    //@Pointcut("execution(* com.example.demo.controller..*.*(..))")
    //指定PostMapping标签
    //@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    //自定义注解
    @Pointcut("@annotation(com.rsa.ReturnDeal)")
    public void pointCut() {
        //处理返回参数
    }

/*    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        System.out.println("------------- doAround.");
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable t){
            t.printStackTrace();
        }
        return obj;
    }*/

/*    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("------------- doAfter.");
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("------------- doBefore.");
    }*/

    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *      参数为Object类型将匹配任何目标返回值
     */
//    @AfterReturning(value = "pointCut()", returning = "result")
//    public void doAfterReturning(JoinPoint joinPoint, String result){
//        System.out.println("doAfterReturning result = " + result);
//    }

/*    @AfterThrowing(value = "pointCut()", throwing = "t")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable t){
        System.out.println("------------- doAfterThrowing throwable = " + t.toString());
    }*/

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     */
/*    @AfterReturning(value = "pointCut()", returning = "keys")
    public Object doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        System.out.println("第一个后置返回通知的返回值：" + keys);
        String KEY = "1234567890hijklm";

        keys = AESUtil.encrypt((String) keys,KEY);

        System.out.println("修改完毕-->返回方法为:" + keys);

        return keys;

    }*/

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("beginning----");
        Object object = pjp.proceed();    //运行doSth()，返回值用一个Object类型来接收
        String KEY = "1234567890hijklm";

        object = AESUtil.encrypt((String) object,KEY);

        System.out.println("ending----");
        return object;
    }


}

