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
@TableName("mh_access_table_column")
public class MhAccessTableColumn implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String comBureauId;
	/**
	 * 
	 */
	private String tblId;
	/**
	 * 
	 */
	private String tblName;
	/**
	 * 
	 */
	private String columnName;
	/**
	 * 
	 */
	private String columnComment;
	/**
	 * 
	 */
	private LocalDateTime accessDate;

}
