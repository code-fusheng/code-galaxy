package xyz.fusheng.user.common.aop;

import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.fusheng.core.model.entity.SelfUser;
import xyz.fusheng.core.utils.SecurityUtils;
import xyz.fusheng.user.common.annotation.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @FileName: RequestAspect
 * @Author: code-fusheng
 * @Date: 2021/6/16 9:27 上午
 * @Version: 1.0
 * @Description: AOP切面输出基本信息
 * 0、@pointcut 配置切入点
 * 1、@Around 环绕通知
 *  在目标方法执行前后实施增强，可以用于日志，事务管理等功能
 * 2、@Before 前置通知
 *  在目标方法执行前实施增强，可以应用于权限管理功能
 * 3、@AfterReturning 后置通知
 *  在目标方法执行后实施增强，可以应用于关闭流、上传文件、删除临时文件等功能
 * 4、@AfterThrowing 异常抛出通知
 *  在方法抛出异常后实施增强，可以应用于处理异常记录日志等功能
 * 5、@DeclareParents 引介通知
 *  在目标类中添加一些新的方法和属性，可以应用于修改老版本程序
 * 6、@After 最终通知
 */

@Aspect
@Component
public class RequestAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestAspect.class);

    /**
     * @Pointcut 切点 指定那些文件需要 AOP
     * execution 切入点表达式
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * xyz.fusheng.*.*.controller..*(..))")
    public void logPointCut() {}

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接受到请求,处理参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
    }

    /**
     * @Around 环绕通知
     * 在目标方法执行前后实施增强，可以应用于日志，事务等功能
     * pjp 是 JoinPoint 的子接口，表示可以执行目标方法
     * 1。必须是Object类型的返回值
     * 2. 必须要接收一个参数
     * 3. 必须使用 throw Throwable
     */
    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 获取被拦截的方法
        Method method = signature.getMethod();
        // 获取此切点的参数
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i ++) {
            if (args[i] instanceof Map<?, ?>) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) args[i];
                logger.info("请求参数:{}", map);
            } else {
                SelfUser userInfo = SecurityUtils.getUserInfo();
                if (ObjectUtils.isNotEmpty(userInfo)) {
                    if (args[i] instanceof SelfUser) {
                        Annotation[] annotations = method.getParameterAnnotations()[i];
                        for (Annotation annotation : annotations) {
                            if (annotation instanceof UserInfo) {
                                logger.info("AOP切面-用户信息:{}", userInfo);
                                args[i] = userInfo;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // pjp.proceed() 执行目标方法 可以理解为对业务方法的模拟
        Object result = pjp.proceed(args);
        // System.currentTimeMillis 获取系统当前时间
        long time = System.currentTimeMillis() - startTime;
        logger.info("耗时 : {}", time);
        return result;
    }

}
