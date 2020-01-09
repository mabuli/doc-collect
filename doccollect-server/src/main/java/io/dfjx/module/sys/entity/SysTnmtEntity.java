package io.dfjx.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 租户ID
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-08-18 21:17:59
 */
@Data
@TableName("sys_tnmt")
public class SysTnmtEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 租户ID
	 */
	@TableId
	private Integer tnmtId;
	/**
	 * 租户名称
	 */
	private String tnmtName;
	/**
	 * 租户中文名称
	 */
	private String tnmtCname;
	/**
	 * 租户类型
	 */
	private Integer tnmtType;

}
