package io.dfjx.module.fm.controller;

import io.dfjx.common.utils.DateUtils;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.module.fm.service.ISysFileService;
import io.dfjx.module.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 附件信息表
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
@RestController
@RequestMapping("sys/file")
public class SysFileController extends AbstractController {
    @Autowired
    private ISysFileService sysFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:form:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysFileService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:form:list")
    public R info(@PathVariable("id") Long id){
        Map<String, Object> info = sysFileService.queryById(id);

        return R.ok().put("info", info);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:form:list")
    public R save(MultipartHttpServletRequest multipartRequest){
        Map<String, Object> info = new HashMap<>();
        info.put("form_id", "1");
        info.put("table_id", multipartRequest.getParameter("table_id"));
        info.put("addby", getUserId());
        info.put("addtime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

        MultipartFile file = multipartRequest.getFile("file");
        String id = info.get("table_id").toString();
        String file_url = sysFileService.saveFile(file, "project/" + id + "/");
        info.put("file_name", file.getOriginalFilename());
        info.put("file_url", file_url);
        sysFileService.insertMap(info);
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        for(Long id : ids){
            Map<String,Object> info = sysFileService.queryById(id);
            String url = info.get("file_url").toString();
            if(url.length()>0)
                sysFileService.delFile(url);
        }
        sysFileService.deleteMap(Arrays.asList(ids));

        return R.ok();
    }


}
