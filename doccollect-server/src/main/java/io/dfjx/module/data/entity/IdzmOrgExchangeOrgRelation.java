package io.dfjx.module.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.dfjx.module.data.vo.ShareRelationOrganVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

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
@TableName("ldzm_org_exchange_org_relation")
public class IdzmOrgExchangeOrgRelation implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 源机构名称
	 */
	private String srcOrgName;

	/**
	 * 目标机构名称
	 */
	private String tgtOrgName;
	/**
	 * 累计交换数据量
	 */
	private String exchangeSum;

	public static ShareRelationOrganVo of(IdzmOrgExchangeOrgRelation model) {
		ShareRelationOrganVo result = new ShareRelationOrganVo();
		if (model != null) {
			BeanUtils.copyProperties(model, result);
		}
		return result;
	}
}
