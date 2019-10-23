package org.xi.quick.sys.models.entity;

import org.xi.quick.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 系统用户Token
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserTokenEntity extends BaseEntity {

    /**
     * UserId
     */
    private Integer userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireAt;
}
