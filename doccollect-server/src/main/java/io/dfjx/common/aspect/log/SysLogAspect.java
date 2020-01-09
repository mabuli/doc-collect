/**
 * 2019 东方金信
 */

package io.dfjx.common.aspect.log;
//import io.domi.modules.sys.service.SysLogService;
import org.aspectj.lang.annotation.Aspect;
        import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Component;
/**
 * 系统日志，切面处理类
 *
 * @author Mark mazong@gmail.com
 */
@Aspect
@Component
public class SysLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private SysLogService sysLogService;
//
//	@Pointcut("execution(* io.domi.modules..controller.*.*(..))")
//	public void logPointCut() {
//
//	}
//
//	@Around("logPointCut()")
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        //请求的类及方法名
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = signature.getName();
//
//        long beginTime = System.currentTimeMillis();
//        //执行方法
//        Object result = joinPoint.proceed();
//        //执行时长(毫秒)
//        long time = System.currentTimeMillis() - beginTime;
//
//        LogManager.me().executeLog(LogTaskFactory.operatorLog(UserContenUtils.getUserId(), className, methodName, joinPoint.getArgs(), result, time));
//
//		return result;
//	}

}
