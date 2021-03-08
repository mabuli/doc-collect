package io.dfjx.module.data.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Data
public class IdzmOrgExchangeOrgRelationVo implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 源机构名称
	 */
	private String srcOrgName;

	/**
	 * 目标机构名称
	 */
	private String tgtOrgName;
	/**
	 * 累计交换数据量
	 */
	private String exchangeSum;

}
