package io.dfjx.module.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.data.entity.LdzmGjfw;
import io.dfjx.module.data.vo.ToolsServiceVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Repository
public interface LdzmGjfwMapper extends BaseMapper<LdzmGjfw> {

	@Select({"select moudle_nm as moudleNm,count(*) as num from gxjh.ldzm_gjfw   group by moudle_nm order by moudle_nm  desc "})
	List<ToolsServiceVo> listToolsServiceVo();
}
