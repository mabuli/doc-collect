package io.dfjx.module.fm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dfjx.core.base.SysBaseEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 项目信息表
 * 
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-12-23 15:04:17
 */
@Mapper
@Repository
public interface FmBaseDao<T> extends BaseMapper<T> {
    @Insert("<script>" +
            "insert ignore into ${tableName}   \n" +
            "      <foreach collection=\"params.keys\" item=\"key\" open=\"(\" close=\")\" separator=\",\" >  \n" +
            "         ${key} \n" +
            "      </foreach>  \n" +
            "      values   \n" +
            "      <foreach collection=\"params.keys\"  item=\"key\" open=\"(\" close=\")\" separator=\",\">  \n" +
            "         #{params.${key}}  \n" +
            "      </foreach>  </script>")
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int insertMap(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("params")Map<String, Object> params);

    @Update("<script>update ${tableName} set\n" +
            "      <foreach item=\"value\" index=\"key\" collection=\"params\" separator=\",\">\n" +
            "          <if test=\"key != tableKey\">${key} = #{value} </if>\n" +
            "      </foreach>\n" +
            "       where \n" +
            "     <foreach item=\"value\" index=\"key\" collection=\"params\" separator=\",\">\n" +
            "         <if test=\"key == tableKey\"> ${key} = #{value} </if>\n" +
            "     </foreach></script>")
    int updateMap(String tableName, @Param("tableKey")String tableKey, @Param("params")Map<String, Object> params);

    @Delete("<script>delete from ${tableName} where ${tableKey} in" +
            "  <foreach collection=\"id\" item=\"key\" open=\"(\" close=\")\" separator=\",\" >  \n" +
            "      ${key} \n" +
            "  </foreach>  \n" +
            "</script>")
    int deleteMap(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("id") Collection<? extends Serializable> id);

    @Delete("delete from ${tableName} where 1=1 ${tableWhere}")
    int deleteWhere(@Param("tableName")String tableName, @Param("tableWhere")String whereStr);

    @Select("select * from ${tableName} where ${tableKey}=#{id}")
    Map<String, Object> selectMapById(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("id")Long id);

    @Select("<script>select * from ${tableName} where 1=1</script>")
    List<Map<String, Object>> queryPage(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("params")Map<String, Object> params, Page pager);

    @Select("<script>select * from ${tableName} where 1=1 ${tableWhere}</script>")
    List<Map<String, Object>> queryAll(@Param("tableName")String tableName, @Param("tableKey")String tableKey, @Param("tableWhere")String whereStr);

    @Select("<script>select count(*) from ${tableName} where 1=1 " +
            "  <foreach item=\"value\" index=\"key\" collection=\"params\" separator=\",\">\n" +
            "      and ${key} = #{value}\n" +
            "  </foreach>\n" +
            "</script>")
    int count(@Param("tableName")String tableName, @Param("params")Map<String, Object> params);
    @Select("<script>select count(*) from ${tableName} where 1=1 ${tableWhere}</script>")
    int countWhere(@Param("tableName")String tableName, @Param("tableWhere")String whereStr);
}
