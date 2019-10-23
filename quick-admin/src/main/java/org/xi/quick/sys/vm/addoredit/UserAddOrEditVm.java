package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.validation.*;
import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.sys.models.entity.UserEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAddOrEditVm extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(groups = {DataEdit.class}, message = "userId (用户ID)不能为空")
    private Integer userId;

    /**
     * 用户名
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "username (用户名)不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "email (邮箱)不能为空")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "mobile (手机号)不能为空")
    private String mobile;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    public UserEntity getUserEntity() {

        UserEntity entity = new UserEntity();
        entity.setUserId(this.userId);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setEmail(this.email);
        entity.setMobile(this.mobile);
        entity.setRealName(this.realName);
        entity.setAvatar(this.avatar);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setUserEntity(UserEntity entity) {

        if (entity == null) return;

        this.userId = entity.getUserId();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
        this.mobile = entity.getMobile();
        this.realName = entity.getRealName();
        this.avatar = entity.getAvatar();
        super.setCurrentEntity(entity);
    }
}
