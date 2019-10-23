package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class CaptchaCondition extends BaseCondition {

    /**
     * 标识
     */
    private String uuid;

    /**
     * 标识 列表
     */
    private List<String> uuidList;

    /**
     * 标识为空
     */
    private Boolean uuidIsEmpty;

    /**
     * 标识
     */
    private String uuidStartWith;

    /**
     * 标识
     */
    private String uuidContains;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码 列表
     */
    private List<String> codeList;

    /**
     * 验证码为空
     */
    private Boolean codeIsEmpty;

    /**
     * 验证码
     */
    private String codeStartWith;

    /**
     * 验证码
     */
    private String codeContains;

    /**
     * 过期时间 列表
     */
    private List<Date> expireAtList;

    /**
     * 最小 过期时间
     */
    private Date expireAtMin;

    /**
     * 最大 过期时间
     */
    private Date expireAtMax;
}
