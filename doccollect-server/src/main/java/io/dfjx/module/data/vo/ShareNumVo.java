package io.dfjx.module.data.vo;

import lombok.Data;

/**
 * @Title:io.dfjx.module.data.vo
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/7 13:24
 * @Version: 1.0
 */
@Data
public class ShareNumVo {

	/**
	 * 当日交换记录数
	 */
	private String exchangeSumD;
	/**
	 * 累计交换记录数
	 */
	private String exchangeSum;
	/**
	 * 当日交换任务数
	 */
	private String exchangeTaskCntD;
	/**
	 * 交换任务总数
	 */
	private String exchangeTaskCnt;
	/**
	 * 当日服务委办局个数
	 */
	private String serviceOrgCntD;
	/**
	 * 累计服务委办局个数
	 */
	private String serviceOrgCnt;


}
