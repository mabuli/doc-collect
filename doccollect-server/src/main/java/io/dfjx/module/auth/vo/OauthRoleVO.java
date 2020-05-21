package io.dfjx.module.auth.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OauthRoleVO implements Serializable {

    /** 主键id */
    private String id;

    /** 角色名称 */
    private String roleName;

    /** 可用状态 0：不可用  1：可用 */
    private Integer status;

}
