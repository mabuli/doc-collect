package io.dfjx.module.fm.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dfjx.core.base.SysBaseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface FmFormDao extends FmBaseDao<SysBaseEntity> {
    @Update("<script>" +
            "alter table ${tableName} add ${params.name} ${params.type} comment '${params.comment}'" +
            "</script>")
    int addField(@Param("tableName") String tableName, @Param("tableKey") String tableKey, @Param("params") Map<String, Object> params);

    @Update("<script>" +
            "alter table ${tableName} drop ${params.name}" +
            "</script>")
    int delField(@Param("tableName") String tableName, @Param("tableKey") String tableKey, @Param("params") Map<String, Object> params);
}
