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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.dfjx.module.sys.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author mazong
 * @email mazong@gmail.com
 * @date 2019-06-20 15:23:47
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    List<SysDeptEntity> queryList(Map<String, Object> params);

    IPage<Map<String, Object>> queryPage(Page pager, @Param("param")Map<String, Object> param);


    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);

}
