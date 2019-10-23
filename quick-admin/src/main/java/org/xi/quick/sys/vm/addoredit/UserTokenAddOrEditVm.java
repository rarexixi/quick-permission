package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.validation.*;
import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.sys.models.entity.UserTokenEntity;

import lombok.*;

import javax.validation.constraints.*;
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
public class UserTokenAddOrEditVm extends BaseEntity {

    /**
     * UserId
     */
    @NotNull(groups = {DataAdd.class}, message = "userId (UserId)不能为空")
    private Integer userId;

    /**
     * token
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "token (token)不能为空")
    private String token;

    /**
     * 过期时间
     */
    @NotNull(groups = {DataAdd.class, DataEdit.class}, message = "expireAt (过期时间)不能为空")
    private Date expireAt;

    public UserTokenEntity getUserTokenEntity() {

        UserTokenEntity entity = new UserTokenEntity();
        entity.setUserId(this.userId);
        entity.setToken(this.token);
        entity.setExpireAt(this.expireAt);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setUserTokenEntity(UserTokenEntity entity) {

        if (entity == null) return;

        this.userId = entity.getUserId();
        this.token = entity.getToken();
        this.expireAt = entity.getExpireAt();
        super.setCurrentEntity(entity);
    }
}
