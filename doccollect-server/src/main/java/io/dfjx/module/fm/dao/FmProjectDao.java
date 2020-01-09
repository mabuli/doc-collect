package io.dfjx.module.fm.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dfjx.core.base.SysBaseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface FmProjectDao extends FmBaseDao<SysBaseEntity> {
    @Insert("<script>" +
            "insert into ${tableName}   \n" +
            "      <foreach collection=\"params.keys\" item=\"key\" open=\"(\" close=\")\" separator=\",\" >  \n" +
            "         ${key} \n" +
            "      </foreach>  \n" +
            "      values   \n" +
            "      <foreach collection=\"params.keys\"  item=\"key\" open=\"(\" close=\")\" separator=\",\">  \n" +
            "         #{params.${key}}  \n" +
            "      </foreach>  </script>")
    @Options(useGeneratedKeys = true, keyProperty = "project_id")
    int insertMap(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("params") Map<String, Object> params);

    @Select("<script>select * from ${tableName} " +
            "<where>" +
            "<if test=\"params.containsKey('project_name') and params.project_name != null and params.project_name != ''\" >" +
            "   project_name like concat('%',#{params.project_name},'%') " +
            "</if>" +
            "<if test=\"params.containsKey('unit_name') and params.unit_name != null and params.unit_name != ''\" >" +
            "   unit_name like concat('%',#{params.unit_name},'%') " +
            "</if>" +
            "</where></script>")
    List<Map<String, Object>> queryPage(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("params")Map<String, Object> params, Page pager);

}
