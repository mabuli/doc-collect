package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

import io.dfjx.module.data.vo.ShareOrganTop5Vo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
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
@TableName("org_exchange_sum_y")
public class OrgExchangeSumY implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String tgtOrgName;
	/**
	 * 
	 */
	private String exchangeSum;
	/**
	 * 
	 */
	private String exchangeTaskCnt;
	public static ShareOrganTop5Vo ofShareOrganTop5Vo(OrgExchangeSumY model) {
		ShareOrganTop5Vo result = new ShareOrganTop5Vo();
		if (model != null) {


		}
		return result;
	}
}
