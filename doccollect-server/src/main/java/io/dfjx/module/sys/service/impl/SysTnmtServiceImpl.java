package io.dfjx.module.sys.service.impl;


import io.dfjx.common.utils.PageUtils;
import io.dfjx.common.utils.Query;
import io.dfjx.module.sys.dao.SysTnmtDao;
import io.dfjx.module.sys.entity.SysTnmtEntity;
import io.dfjx.module.sys.service.SysTnmtService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("sysTnmtService")
public class SysTnmtServiceImpl extends ServiceImpl<SysTnmtDao, SysTnmtEntity> implements SysTnmtService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysTnmtEntity> page = this.page(
                new Query<SysTnmtEntity>().getPage(params),
                new QueryWrapper<SysTnmtEntity>()
        );

        return new PageUtils(page);
    }

}