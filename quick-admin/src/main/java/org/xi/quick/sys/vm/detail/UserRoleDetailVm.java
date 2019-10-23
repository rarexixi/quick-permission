package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.UserRoleEntity;
import org.xi.quick.sys.models.entity.extension.UserRoleEntityExtension;

import lombok.*;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDetailVm extends BaseEntity {

    public UserRoleDetailVm(UserRoleEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        roleId = entity.getRoleId();
    }

    public UserRoleDetailVm(UserRoleEntity entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        roleId = entity.getRoleId();
    }

    /**
     * 用户ID
     */
    @ExcelCol("用户ID")
    private Integer userId;

    /**
     * 角色ID
     */
    @ExcelCol("角色ID")
    private Integer roleId;
}
