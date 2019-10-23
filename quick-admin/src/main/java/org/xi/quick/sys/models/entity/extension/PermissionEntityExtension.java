package org.xi.quick.sys.models.entity.extension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xi.quick.sys.models.entity.PermissionEntity;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionEntityExtension extends PermissionEntity {

    /**
     * 类型 (0模块,1权限)
     */
    private String typeText;
}
