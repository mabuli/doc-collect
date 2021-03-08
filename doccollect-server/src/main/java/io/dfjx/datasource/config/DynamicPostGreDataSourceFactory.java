package io.dfjx.datasource.config;/**
 * <h3>doc-collect</h3>
 * <p>postgre 数据源</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 22:01
 **/

import com.alibaba.druid.pool.DruidDataSource;
import io.dfjx.datasource.properties.PostGreSourceProperties;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
public class DynamicPostGreDataSourceFactory {
    public static DruidDataSource buildDruidDataSource(PostGreSourceProperties postGreSourceProperties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(postGreSourceProperties.getUrl());
        datasource.setUsername(postGreSourceProperties.getUsername());
        datasource.setPassword(postGreSourceProperties.getPassword());
        datasource.setDriverClassName(postGreSourceProperties.getDriverClassName());

        return datasource;
    }
}
