package io.dfjx.module.data.vo;

import io.dfjx.module.data.entity.OrgExchangeSumM;
import lombok.Data;

/**
 * @Title:io.dfjx.module.data.vo
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/7 11:25
 * @Version: 1.0
 */
@Data
public class ShareOrganTop5Vo {
	/**
	 * 机构名
	 */
	private String tgtOrgName;
	/**
	 * 条数
	 */
	private String exchangeSum;


}
