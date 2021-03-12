package io.dfjx.module.data.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Title:io.dfjx.module.query.controller
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/6 16:57
 * @Version: 1.0
 */
@Data
public class ShareRelationOrganVo implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id;


	private String name;

	private Double symbolSize;

	private Long value;

}
