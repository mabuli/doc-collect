package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.DxApiListMapper;
import io.dfjx.module.data.entity.DxApiList;
import io.dfjx.module.data.service.IDxApiListService;
import io.dfjx.module.data.vo.TerraceSupportVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

@DataSource(value = "postGreSource")

@Service("dxApiListService")
public class DxApiListServiceImpl extends ServiceImpl<DxApiListMapper, DxApiList>
		implements IDxApiListService {

	@Autowired
	private DxApiListMapper mapper;


	@Override
	public TerraceSupportVo getTerraceSupportVo() {
		return mapper.getTerraceSupportVo();
	}

}
