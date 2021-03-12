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
@TableName("mh_data_access_expand_details")
public class MhDataAccessExpandDetails implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String sssq;
	/**
	 * 
	 */
	private String comBureauId;
	/**
	 * 
	 */
	private String comBureauNm;
	/**
	 * 
	 */
	private String dataSrcId;
	/**
	 * 
	 */
	private String dataSrcNm;
	/**
	 * 
	 */
	private String tblName;
	/**
	 * 
	 */
	private String tabComment;
	/**
	 * 
	 */
	private String tblType;
	/**
	 * 
	 */
	private Long recodAmount;
	/**
	 * 
	 */
	private String totalsize;
	/**
	 * 
	 */
	private String updateCycle;
	/**
	 * 
	 */
	private String totalIncremental;
	/**
	 * 
	 */
	private LocalDateTime accessDate;
	/**
	 * 
	 */
	private LocalDateTime tabCreateTime;
	/**
	 * 
	 */
	private String tblId;
	/**
	 * 
	 */
	private String gkName;
	/**
	 * 
	 */
	private String gkCode;

}
