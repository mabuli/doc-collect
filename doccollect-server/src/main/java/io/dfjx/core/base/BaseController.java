package io.dfjx.core.base;

import io.dfjx.core.util.common.QRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 通用展示控制类
 * Created by cc on 2018/7/30.
 */
public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;
    protected UrlPathHelper urlPathHelper; // 路径助手

    /**
     * 设置客户端缓存参数
     *
     * @param response
     */
    private void setWebCache(HttpServletResponse response) {
        // 将过期日期设置为一个 未来时间
        // response.setHeader("Expires", "Wed, 09 Jan 2013 23:55:10 GMT");
        // 设置 HTTP/1.1 no-cache 头 ,三天更新一次缓存
        response.setHeader("Cache-Control", "public, max-age=259200");

        // 设置标准 HTTP/1.0 no-cache header. ,三天更新一次缓存
        response.setHeader("Pragma", "public, max-age=259200");
    }



    public void before(HttpServletRequest request, HttpServletResponse response,
                             Object handler){
        this.urlPathHelper = new UrlPathHelper();
        this.request = request;
        this.response = response;
    }
    public void after(HttpServletRequest request,
                            HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) {
        if (modelAndView == null)
            return;
        String viewName = modelAndView.getViewName();

        if (viewName == null || "".equals(viewName)) {
            //modelAndView.clear();
        } else if (viewName.startsWith("redirect:")) {
            // modelAndView.addAllObjects(_ASSIGN_);
        } else {
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath();

            modelAndView.addObject("host", url);

        }
    }

    // <editor-fold desc="数据辅助">
    public String rstr(Map<String, Object> map, String name){
        return rstr(map, name, "");
    }

    public String rstr(Map<String, Object> map, String name, String dfval){
        if(!map.containsKey(name))
            return dfval;
        Object obj = map.get(name);
        if(obj == null)
            return dfval;
        return obj.toString();
    }
    public String rstr(String name){
        return QRequest.getString(request, name);
    }
    public String rstr(String name, String dfval){
        return QRequest.getString(request, name, dfval);
    }
    public Integer rint(String name){
        return QRequest.getInteger(request, name);
    }
    public Integer rint(String name, Integer dfval){
        return QRequest.getInteger(request, name, dfval);
    }
    // </editor-fold>
}
