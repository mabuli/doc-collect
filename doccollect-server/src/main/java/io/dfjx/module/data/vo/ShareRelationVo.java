package io.dfjx.module.data.vo;

import lombok.Data;

import java.util.List;

/**
 * @Title:io.dfjx.module.data.vo
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/8 5:09
 * @Version: 1.0
 */
@Data
public class ShareRelationVo {

	private List<ShareRelationOrganVo> data;
	private List<LinkVo> links;
}

