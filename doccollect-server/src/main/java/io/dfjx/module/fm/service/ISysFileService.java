package io.dfjx.module.fm.service;

import io.dfjx.core.base.SysBaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 数据源
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-08-18 21:17:59
 */
public interface ISysFileService extends IFmBaseService<SysBaseEntity> {
    boolean delFile(String url);
    String saveFile(MultipartFile file, String subDir);
}

