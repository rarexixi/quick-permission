package org.xi.quick.sys.models.entity.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xi.quick.sys.models.entity.RoleEntity;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleEntityExtension extends RoleEntity {

    /**
     * 是否内置角色 (0否, 1是)
     */
    private String builtInText;
}
