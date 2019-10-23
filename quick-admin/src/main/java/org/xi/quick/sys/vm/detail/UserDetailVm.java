package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.UserEntity;
import org.xi.quick.sys.models.entity.extension.UserEntityExtension;

import lombok.*;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailVm extends BaseEntity {

    public UserDetailVm(UserEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        username = entity.getUsername();
        password = entity.getPassword();
        salt = entity.getSalt();
        email = entity.getEmail();
        mobile = entity.getMobile();
        realName = entity.getRealName();
        avatar = entity.getAvatar();
    }

    public UserDetailVm(UserEntity entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        username = entity.getUsername();
        password = entity.getPassword();
        salt = entity.getSalt();
        email = entity.getEmail();
        mobile = entity.getMobile();
        realName = entity.getRealName();
        avatar = entity.getAvatar();
    }

    /**
     * 用户ID
     */
    @ExcelCol("用户ID")
    private Integer userId;

    /**
     * 用户名
     */
    @ExcelCol("用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelCol("密码")
    private String password;

    /**
     * 盐
     */
    @ExcelCol("盐")
    private String salt;

    /**
     * 邮箱
     */
    @ExcelCol("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ExcelCol("手机号")
    private String mobile;

    /**
     * 姓名
     */
    @ExcelCol("姓名")
    private String realName;

    /**
     * 头像
     */
    @ExcelCol("头像")
    private String avatar;
}
