package io.dfjx.module.fm.dao;

import io.dfjx.core.base.SysBaseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FmComDao extends FmBaseDao<SysBaseEntity> {

}
