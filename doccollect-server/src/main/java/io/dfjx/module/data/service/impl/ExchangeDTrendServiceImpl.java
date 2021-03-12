package io.dfjx.module.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.ExchangeDTrendMapper;
import io.dfjx.module.data.entity.ExchangeDTrend;
import io.dfjx.module.data.service.IExchangeDTrendService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DataSource(value = "postGreSource")

@Service("exchangeDTrendService")
public class ExchangeDTrendServiceImpl extends ServiceImpl<ExchangeDTrendMapper, ExchangeDTrend> implements IExchangeDTrendService {

	@Autowired
	private ExchangeDTrendMapper mapper;


	@Override
	public List<ExchangeDTrend> listExchangeDTrend(IPage<ExchangeDTrend> page) {
		return this.lambdaQuery().orderByDesc(ExchangeDTrend::getOperateDate).page(page).getRecords();
	}
}
