package io.dfjx.module.fm.service.impl;

import com.alibaba.druid.util.StringUtils;
import io.dfjx.common.exception.RRException;
import io.dfjx.core.base.SysBaseEntity;
import io.dfjx.module.fm.dao.FmComDao;
import io.dfjx.module.fm.service.ISysFileService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service("sysFileService")
public class SysFileServiceImpl extends FmBaseServiceImpl<FmComDao, SysBaseEntity>  implements ISysFileService {
    @Value("${system-config.file-dir}")
    private String fileDir;
    private static final String UPLOAD_DIR = "upload/";

    public SysFileServiceImpl(){
        BaseTable = "sys_file";
        BaseKey = "id";
    }

    @Override
    public String getWhere(Map<String, Object> params){
        String str = " and form_id='" + mstr(params, "f") + "'";
        return str;
    }

    @Override
    public String saveFile(MultipartFile file, String subDir) {
        String oldFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");
        String suffix = oldFileName.indexOf(".") != -1 ? oldFileName.substring(oldFileName.lastIndexOf(".")) : ".png";
        // 构成新文件名
        String fileName = uuid + suffix;
        String fullDir = getFullFileDir(subDir);
        String fileUrl = UPLOAD_DIR + subDir + fileName;
        String fullPath = fullDir + fileName;
        File uploadFile = new File(fullPath);
        try {
            FileCopyUtils.copy(file.getBytes(), uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RRException("无法保存上传图片，"+e.getMessage());
        }
        return fileUrl;
    }

    public boolean delFile(String url){
        String subdir = url.replace(UPLOAD_DIR, "").replace("/", File.separator);
        String filePath = fileDir + File.separator + subdir;
        File f = new File(filePath);
        return f.delete();
    }

    private String getFullFileDir(String subDir){
        String fullDir = fileDir + subDir.replace("/", File.separator);
        File dirPath = new File(fullDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        return fullDir;
    }
}
