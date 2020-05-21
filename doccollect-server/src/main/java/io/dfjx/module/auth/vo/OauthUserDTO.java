package io.dfjx.module.auth.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author makaiyu
 * @date 2019/5/13 18:02
 */
@Data
public class OauthUserDTO implements Serializable {

    private static final long serialVersionUID = 74335887421213094L;
    /** 主键id */
    private Long id;

    /** 登录账号 */
    private String username;

    /** 名称 */
    private String name;

    /**角色id*/
    private List<OauthRoleVO> roles;
}
