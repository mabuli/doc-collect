package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("sd_field_infornation")
public class SdFieldInfornation implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String deptId;
	/**
	 * 
	 */
	private String deptName;
	/**
	 * 
	 */
	private String sysId;
	/**
	 * 
	 */
	private String sysName;
	/**
	 * 
	 */
	private String sourceEname;
	/**
	 * 
	 */
	private String oriApiName;
	/**
	 * 
	 */
	private String sourceCnam;
	/**
	 * 
	 */
	private String fieldId;
	/**
	 * 
	 */
	private String fieldEnama;
	/**
	 * 
	 */
	private String fieldCname;
	/**
	 * 
	 */
	private String fieldType;
	/**
	 * 
	 */
	private String pk;
	/**
	 * 
	 */
	private String updateDate;
	/**
	 * 
	 */
	private String notes;
	/**
	 * 
	 */
	private String cleanRule;

}
