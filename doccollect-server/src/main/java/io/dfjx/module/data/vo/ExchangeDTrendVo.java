package io.dfjx.module.data.vo;

import lombok.Data;

/**
 * @Title:io.dfjx.module.data.vo
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/7 13:45
 * @Version: 1.0
 */
@Data
public class ExchangeDTrendVo {

	/**
	 *日期
	 */
	private String operateDate;
	/**
	 * 交换记录数
	 */
	private String exchangeSum;
	/**
	 * 交换任务数
	 */
	private String exchangeTaskCnt;
}
