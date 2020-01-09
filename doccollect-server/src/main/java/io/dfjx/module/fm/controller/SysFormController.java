package io.dfjx.module.fm.controller;

import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.module.fm.service.IFmProjectService;
import io.dfjx.module.fm.service.ISysFormFldService;
import io.dfjx.module.fm.service.ISysFormService;
import io.dfjx.module.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目信息表
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
@RestController
@RequestMapping("sys/form")
public class SysFormController extends AbstractController {
    @Autowired
    private ISysFormService sysFormService;
    @Autowired
    private ISysFormFldService sysFormFldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:form:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysFormService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:form:list")
    public R info(@PathVariable("id") Long id){
        Map<String, Object> fmProject = sysFormService.queryById(id);

        return R.ok().put("info", fmProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:form:list")
    public R save(@RequestBody Map<String, Object> fmProject){
        sysFormService.insertMap(fmProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:form:list")
    public R update(@RequestBody Map<String, Object> fmProject){
        sysFormService.updateMap(fmProject);

        return R.ok();
    }

    /**
     * 字段列表
     */
    @RequestMapping("/listfld")
    //@RequiresPermissions("sys:form:list")
    public R listfld(@RequestParam Map<String, Object> params){
        List<Map<String,Object>> page = sysFormFldService.queryAll(params);

        return R.ok().put("page", page);
    }

    /**
     * 新增字段
     */
    @RequestMapping("/addfld")
    //@RequiresPermissions("sys:form:list")
    public R addfld(@RequestBody Map<String, Object> params){
        R res;
        //添加字段
        res = sysFormFldService.insertColumn(params);
        if(!res.isok())
            return res;
        //更新表格
        res = updatefld(params);
        return res;
    }

    @RequestMapping("/updatefld")
    //@RequiresPermissions("sys:form:list")
    public R updatefld(@RequestBody Map<String, Object> params){
        R res = R.ok();
        String json = params.get("json_config").toString();
        //更新表格
        Map<String, Object> map = new HashMap<>();
        map.put("json_config", json);
        map.put("id", 1);
        sysFormService.updateMap(map);
        return res;
    }

    @RequestMapping("/delfld")
    //@RequiresPermissions("sys:form:list")
    public R delfld(@RequestBody Map<String, Object> params){
        R res;
        //删除字段
        res = sysFormFldService.delColumn(params);
        if(!res.isok())
            return res;
        //更新表格
        res = updatefld(params);
        return res;
    }
}
