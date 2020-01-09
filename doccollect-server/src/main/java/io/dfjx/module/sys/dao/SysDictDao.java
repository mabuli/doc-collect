/**
 * Copyright 2018 东方金信
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.dfjx.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjx.module.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark mazong@gmail.com
 * @since 3.1.0 2018-01-27
 */
@Mapper
@Repository
public interface SysDictDao extends BaseMapper<SysDictEntity> {
    @Select("<script>select distinct name, type value from sys_dict where del_flag=0</script>")
    List<Map<String, Object>> types();

    @Select("<script>select code name,value from sys_dict where del_flag=0 and type=#{type}</script>")
    List<Map<String, Object>> values(@Param("type")String type);
}
