package io.dfjx.module.query.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.dfjx.common.aspect.log.LogManager;
import io.dfjx.common.aspect.log.LogTaskFactory;
import io.dfjx.common.utils.UserContenUtils;
import io.dfjx.module.sys.entity.SysLogEntity;
import io.dfjx.module.sys.entity.SysMenuEntity;
import io.dfjx.module.sys.entity.SysUserEntity;
import io.dfjx.module.sys.service.SysLogService;
import io.dfjx.module.sys.service.SysMenuService;
import io.dfjx.module.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * 对ProxyController类中方法进行记录日志操作
 */
@Aspect
@Component
public class ProxyAspect {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Value("${system-config.project-url}")
    private String projectUrl;

    /**
     * 定义切入点
     * io.dfjx.module.query.controller当前包及其子包内的类
     */
//    @Pointcut("execution(* io.dfjx.module.query.controller.ProxyController.*(..))")
    @Pointcut("execution(* io.dfjx.module.query.controller..*.*(..))")
    public void pointcut(){ }

    /**
     * 环形切面，进行记录日志
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        // 获取类名
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        //执行方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的URI
        String ip = getIpAddr(request);

        //请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        String referer = "";
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if(key.equals("referer")) {
                referer = value;
            }
        }

        // 记录开始时间
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object result = null;
        String resultTxt = null;
        try{
            result = proceedingJoinPoint.proceed();
            resultTxt = "成功";
        } catch (Throwable t) {
            resultTxt = "失败";
        }
        long end = System.currentTimeMillis();

        LogManager.me().executeLog(LogTaskFactory.operatorLog(UserContenUtils.getUserId(), className, methodName, proceedingJoinPoint.getArgs(), result, end-start));
        //获取当前用户信息
        Long userId = UserContenUtils.getUserId();
        SysUserEntity user = sysUserService.getById(userId);
        //查询用户操作
        String operation = "";
        List<SysMenuEntity> list = sysMenuService.list(new QueryWrapper<SysMenuEntity>().like(true, "url", getRefererUrl(request, referer)));
        if(!list.isEmpty()) {
            operation = list.get(0).getName();
        }

        //执行插入数据库
        SysLogEntity log = new SysLogEntity();
        log .setOperation(operation)
            .setResult(resultTxt)
            .setTime(end-start)
            .setIp(ip)
            .setCreateDate(new Date());
        if(user != null) {
            log.setUsername(user.getUserRealName());
        }

        sysLogService.save(log);
        return result;
    }

    private String getRefererUrl(HttpServletRequest request, String referer) {
        String url = null;
        if(StringUtils.isEmpty(projectUrl)) {
            String schema = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String contextPath = request.getContextPath();
            url = referer.replaceAll(schema+"://"+serverName + ":" + port + contextPath+"/", "");
        } else {
            url = referer.replaceAll(projectUrl, "");
        }
        return url;
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ipAddress)?"127.0.0.1":ipAddress;
    }

}
