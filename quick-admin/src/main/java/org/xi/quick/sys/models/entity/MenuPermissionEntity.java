package org.xi.quick.sys.models.entity;

import lombok.*;
import org.xi.quick.common.model.BaseEntity;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuPermissionEntity extends BaseEntity {

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 权限ID
     */
    private Integer permissionId;
}
