package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.dfjx.module.data.vo.ShareNumVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exchange_sum_task")
public class ExchangeSumTask implements Serializable {
	private static final long serialVersionUID = 1L;
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


	public static ShareNumVo ofShareNumVo(ExchangeSumTask model) {
		ShareNumVo result = new ShareNumVo();
		if (model != null) {
			BeanUtils.copyProperties(model, result);
		}
		return result;
	}
}
