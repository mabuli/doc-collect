package io.dfjx.module.data.vo;

import lombok.Data;

/**
 * @Title:io.dfjx.module.data.vo
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/7 17:49
 * @Version: 1.0
 */
@Data
public class TerraceSupportVo {

	/**
	 * 城运平台 数量
	 */
	private String cyptNum;
	/**
	 * 城运平台 比例
	 */
	private String cyptRatio;
	/**
	 * 实时决策 数量
	 */
	private String ssjcNum;
	/**
	 * 实时决策 比例
	 */
	private String ssjcRatio;
	/**
	 * 信用平台 数量
	 */
	private String xyptNum;
	/**
	 * 信用平台 比例
	 */
	private String xyptRatio;
}
