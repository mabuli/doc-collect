package io.dfjx.module.fm.controller;

import io.dfjx.common.utils.DateUtils;
import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.R;
import io.dfjx.module.fm.service.ISysFileService;
import io.dfjx.module.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
        PageUtils page = sysFileService.queryList(params);
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

    @Value("${system-config.file-dir}")
    private String saveDir;
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void downExcel(HttpServletResponse response,@RequestParam("tranFileName") String tranFileName,@RequestParam("realFileName")String realFileName) throws Exception {
        String filepath = saveDir + tranFileName;
        // 如果文件名不为空，则进行下载
        if (tranFileName != null) {
            File file = new File(filepath);
            // 如果文件存在，则进行下载
            if (file.exists()) {
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(realFileName, "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                bis.close();
                fis.close();
            }
        }
    }
}
