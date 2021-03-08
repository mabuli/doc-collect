package io.dfjx.datasource.properties;/**
 * <h3>doc-collect</h3>
 * <p>postgre数据源</p>
 *
 * @author : PanhuGao
 * @date : 2021-03-07 21:53
 **/

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @创建人 PanHu.Gao
 * @创建时间 2021/3/7
 * @描述
 */
@Data
@Component
@ConfigurationProperties(prefix = "postgre")
public class PostGreSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String platform;
}
