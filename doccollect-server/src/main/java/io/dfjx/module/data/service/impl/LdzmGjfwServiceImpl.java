package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.LdzmGjfwMapper;
import io.dfjx.module.data.entity.LdzmGjfw;
import io.dfjx.module.data.service.ILdzmGjfwService;
import io.dfjx.module.data.vo.ToolsServiceVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DataSource(value = "postGreSource")

@Service("ldzmGjfwService")
public class LdzmGjfwServiceImpl extends ServiceImpl<LdzmGjfwMapper, LdzmGjfw> implements ILdzmGjfwService {

	@Autowired
	private LdzmGjfwMapper mapper;

	@Override
	public List<ToolsServiceVo> listToolsServiceVo() {

		return mapper.listToolsServiceVo();
	}
}
