package io.dfjx.core.base;

import io.dfjx.common.utils.PageUtils;
import io.dfjx.core.model.JsonDataGrid;
import io.dfjx.core.util.common.QRequest;
import io.dfjx.core.model.BO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 通用展示控制类
 * Created by cc on 2018/7/30.
 */
public class SysBaseController extends BaseController {


    public ISysBaseService getBaseService() {
        return null;
    }

    @RequestMapping("/getlist")
    public BO getlist(@RequestParam Map<String, Object> params){
        PageUtils page = getBaseService().queryPage(params);
        return BO.ok(page);
    }

    @RequestMapping(value = "/getdata")
    @ResponseBody
    //@PostMapping(value = "/getdata")
    public Object getdata(@RequestParam Map<String, Object> params){
        List list = getBaseService().queryAll(params);
        String jsonType = rstr("json", "data");
        String str = getJson(jsonType, list, list.size(), rstr("fieldShow"), "");

        try {
            response.getWriter().print(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取js代码
     *
     * @return
     * @throws IOException
     */
    private String getJson(String json, List<Map<String, Object>> dt, int totalCount, String fieldShow, String fieldOrder) {
        String str = "";
        // 日期格式
        String dateTimeFormat = QRequest.getString(request, "dateTimeFormat", "yyyy-MM-dd"); // 例：dateTimeFormat=yyyy-MM-dd HH:mm
        // 默认输出html(?)
        // String contentType = QRequest.getString(request, "responseType",
        // "json"); //输出json 为： responseType=json
        String tbName = "";
        response.setContentType(QRequest.getResponseType("json")); // 输出JS文件
        switch (json) {
            case "data": // 全表格[{}]
                str = new JsonDataGrid(totalCount, dt).toDataJson(dateTimeFormat);
                break;
            case "dataJs":
                String jsName = QRequest.getString(request, "name");
                if (jsName.length() == 0)
                    jsName = tbName;
                response.setContentType("application/x-javascript; charset=UTF-8"); // 输出JS文件
                str = MessageFormat.format("var _dataStore[\"{0}\"] = {1} ;",
                        jsName, new JsonDataGrid(totalCount, dt).toDataJson(dateTimeFormat));
                break;
            case "page": // 分页表格{total:0,rows:[]}
                str = new JsonDataGrid(totalCount, dt).toJson(dateTimeFormat);
                break;
            case "tree": // 树下拉[{id:0,text:"",children:[]}]
                // fieldTree:id列名,text列名,parentid列名,开始节点id
                String fieldTree = QRequest.getString(request, "fieldTree");
                str = new JsonDataGrid(totalCount, dt).toTreeJson(fieldTree);
                break;

            case "obj":
                str = new JsonDataGrid(totalCount, dt).toObjJson();
                break;
            case "combo":
                str = new JsonDataGrid(totalCount, dt).toComboJson(fieldShow);
                break;
            case "store": // 数据仓库
                response.setContentType("application/x-javascript; charset=UTF-8"); // 输出JS文件
                String storeName = QRequest.getString(request, "name");
                if (storeName.length() == 0)
                    storeName = tbName;
                str = new JsonDataGrid(totalCount, dt).writeDataStoreJs(fieldShow,
                        storeName);
                break;
        }
        return str;
    }

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
}
