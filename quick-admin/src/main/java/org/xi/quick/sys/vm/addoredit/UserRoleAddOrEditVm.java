package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.sys.models.entity.UserRoleEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleAddOrEditVm extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(groups = {DataAdd.class}, message = "userId (用户ID)不能为空")
    private Integer userId;

    /**
     * 角色ID
     */
    @NotNull(groups = {DataAdd.class}, message = "roleId (角色ID)不能为空")
    private Integer roleId;

    public UserRoleEntity getUserRoleEntity() {

        UserRoleEntity entity = new UserRoleEntity();
        entity.setUserId(this.userId);
        entity.setRoleId(this.roleId);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setUserRoleEntity(UserRoleEntity entity) {

        if (entity == null) return;

        this.userId = entity.getUserId();
        this.roleId = entity.getRoleId();
        super.setCurrentEntity(entity);
    }
}
