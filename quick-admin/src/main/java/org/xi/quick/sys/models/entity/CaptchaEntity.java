package org.xi.quick.sys.models.entity;

import org.xi.quick.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class CaptchaEntity extends BaseEntity {

    /**
     * 标识
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private Date expireAt;
}
