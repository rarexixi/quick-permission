package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class ConfigCondition extends BaseCondition {

    /**
     * 配置键
     */
    private String key;

    /**
     * 配置键 列表
     */
    private List<String> keyList;

    /**
     * 配置键为空
     */
    private Boolean keyIsEmpty;

    /**
     * 配置键
     */
    private String keyStartWith;

    /**
     * 配置键
     */
    private String keyContains;
}
