package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.ConfigCondition;
import org.xi.quick.sys.models.condition.extension.ConfigConditionExtension;

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
public class ConfigSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

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

    public ConfigCondition getCondition() {

        return getConditionExtension();
    }

    public ConfigConditionExtension getConditionExtension() {

        ConfigConditionExtension condition = new ConfigConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setKey(this.key);
        condition.setKeyList(this.keyList);
        condition.setKeyIsEmpty(this.keyIsEmpty);
        condition.setKeyStartWith(this.keyStartWith);
        condition.setKeyContains(this.keyContains);
        return condition;
    }
}
