package io.dfjx.module.fm.controller;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

import io.dfjx.common.annotation.SysLog;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.module.fm.service.IFmProjectService;
import io.dfjx.module.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



/**
 * 项目信息表
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
@RestController
@RequestMapping("fm/project")
public class FmProjectController extends AbstractController {
    @Autowired
    private IFmProjectService fmProjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("fm:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fmProjectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{projectId}")
    @RequiresPermissions("fm:project:info")
    public R info(@PathVariable("projectId") Long projectId){
        Map<String, Object> fmProject = fmProjectService.queryById(projectId);

        return R.ok().put("info", fmProject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("fm:project:save")
    public R save(@RequestBody Map<String, Object> fmProject){
		fmProjectService.insertMap(fmProject);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("fm:project:update")
    public R update(@RequestBody Map<String, Object> fmProject){
        R r = fmProjectService.validValue(fmProject);
        if(!r.isok()) return r;
		fmProjectService.updateMap(fmProject);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除项目")
    @RequestMapping("/delete")
    @RequiresPermissions("fm:project:delete")
    public R delete(@RequestBody Long[] projectIds){
        fmProjectService.deleteMap(Arrays.asList(projectIds));
        return R.ok();
    }

    @RequestMapping("/upload")
    @RequiresPermissions("fm:project:upload")
    public R upload(MultipartHttpServletRequest multipartRequest) {
        long mb = 1024 * 1024;
        MultipartFile file = multipartRequest.getFile("file");
        String type=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(!".doc".equals(type)&&!".docx".equals(type)){
            return R.error("格式错误，请重新上传正确的格式");
        }
        if (file.isEmpty()) {
            return R.error("解析失败，请检查文件后重新上传");
        }
        if(file.getSize()>(mb*10)) {
            return R.error("文件超过10M，请压缩后重新上传");
        }
        try {
            InputStream is  = file.getInputStream();
            R res = fmProjectService.importFile(is, type);
            if(is != null)
                is.close();
            return res;

        } catch (IOException e) {
            e.printStackTrace();
            return R.error("读取文档失败，请检查文件后重新上传");
        }
    }

}
