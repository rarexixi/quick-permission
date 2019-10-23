package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.UserTokenEntity;
import org.xi.quick.sys.models.entity.extension.UserTokenEntityExtension;

import lombok.*;

import java.util.*;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTokenDetailVm extends BaseEntity {

    public UserTokenDetailVm(UserTokenEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        token = entity.getToken();
        expireAt = entity.getExpireAt();
    }

    public UserTokenDetailVm(UserTokenEntity entity) {
        super(entity);

        if (entity == null) return;
        userId = entity.getUserId();
        token = entity.getToken();
        expireAt = entity.getExpireAt();
    }

    /**
     * UserId
     */
    @ExcelCol("UserId")
    private Integer userId;

    /**
     * token
     */
    @ExcelCol("token")
    private String token;

    /**
     * 过期时间
     */
    @ExcelCol(value = "过期时间", formatter = "yyyy-MM-dd HH:mm")
    private Date expireAt;
}
