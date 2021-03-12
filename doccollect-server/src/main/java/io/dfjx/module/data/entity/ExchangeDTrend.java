package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import io.dfjx.module.data.vo.ExchangeDTrendVo;
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
@TableName("exchange_d_trend")
public class ExchangeDTrend implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private String operateDate;
	/**
	 *
	 */
	private String exchangeSum;
	/**
	 *
	 */
	private String exchangeTaskCnt;

	public static ExchangeDTrendVo ofExchangeDTrendVo(ExchangeDTrend model) {
		ExchangeDTrendVo result = new ExchangeDTrendVo();
		if (model != null) {
			BeanUtils.copyProperties(model, result);
		}
		return result;
	}

}
