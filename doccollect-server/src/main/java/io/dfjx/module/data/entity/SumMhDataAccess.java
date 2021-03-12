package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;
import java.io.Serializable;
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
@TableName("sum_mh_data_access")
public class SumMhDataAccess implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String currWbjAmount;
	/**
	 * 
	 */
	private String currTabAmount;
	/**
	 * 
	 */
	private BigDecimal currRecodAmount;
	/**
	 * 
	 */
	private BigDecimal currFileAmount;
	/**
	 * 
	 */
	private String tolWbjAmount;
	/**
	 * 
	 */
	private String tolTabAmount;
	/**
	 * 
	 */
	private BigDecimal tolRecodAmount;
	/**
	 * 
	 */
	private BigDecimal tolFileAmount;
	/**
	 * 
	 */
	private LocalDateTime accessDate;
	/**
	 * 
	 */
	private BigDecimal currDocSize;
	/**
	 * 
	 */
	private BigDecimal currDocAmount;
	/**
	 * 
	 */
	private BigDecimal tolDocSize;
	/**
	 * 
	 */
	private BigDecimal tolDocAmount;
	/**
	 * 
	 */
	private BigDecimal actRecodAmount;

}
