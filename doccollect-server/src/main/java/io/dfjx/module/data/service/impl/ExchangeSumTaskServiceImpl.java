package io.dfjx.module.data.service.impl;

import io.dfjx.datasource.annotation.DataSource;
import io.dfjx.module.data.dao.ExchangeSumTaskMapper;
import io.dfjx.module.data.entity.ExchangeSumTask;
import io.dfjx.module.data.service.IExchangeSumTaskService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DataSource(value = "postGreSource")

@Service("exchangeSumTaskService")
public class ExchangeSumTaskServiceImpl extends ServiceImpl<ExchangeSumTaskMapper, ExchangeSumTask> implements IExchangeSumTaskService {

	@Autowired
	private ExchangeSumTaskMapper mapper;


	@Override
	public List<ExchangeSumTask> listExchangeSumTask() {
		return this.lambdaQuery().list();
	}
}
