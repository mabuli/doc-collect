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
@TableName("ldzm_gjfw")
public class LdzmGjfw implements Serializable {
private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id", type = IdType.INPUT)
	private Integer id;
	/**
	 * 
	 */
	private String moudleNm;
	/**
	 * 
	 */
	private String orgNm;
	/**
	 * 
	 */
	private String comment;

}
