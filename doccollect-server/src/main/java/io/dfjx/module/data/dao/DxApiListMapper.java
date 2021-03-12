package io.dfjx.module.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.data.entity.DxApiList;
import io.dfjx.module.data.vo.TerraceSupportVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Repository
public interface DxApiListMapper extends BaseMapper<DxApiList> {

	@Select({
			"SELECT SUM  " +
					" ( CASE WHEN SYSTEM = '城运平台' THEN '1' ELSE 0 END ) AS cyptNum,  " +
					" round(sum(case when system = '城运平台'  then '1' else 0 end )/cast(count(*) as numeric),2)*100 ||'%' AS cyptRatio,  " +
					" SUM ( CASE WHEN SYSTEM = '实时决策' THEN '1' ELSE 0 END ) AS ssjcNum,  " +
					"  round(sum(case when system = '实时决策'  then '1' else 0 end )/cast(count(*) as numeric),2)*100 ||'%' AS ssjcRatio,  " +
					" SUM ( CASE WHEN SYSTEM = '信用平台' THEN '1' ELSE 0 END ) AS xyptNum,  " +
					"  round(sum(case when system = '信用平台'  then '1' else 0 end )/cast(count(*) as numeric),2)*100 ||'%' as xyptRatio   " +
					"FROM  " +
					" dx_api_list;"
	})
	TerraceSupportVo getTerraceSupportVo();

}
