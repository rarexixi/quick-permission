package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.OptionCondition;
import org.xi.quick.sys.models.condition.extension.OptionConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class OptionSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 枚举ID
     */
    private Integer id;

    /**
     * 枚举ID 列表
     */
    private List<Integer> idList;

    /**
     * 最小 枚举ID
     */
    private Integer idMin;

    /**
     * 最大 枚举ID
     */
    private Integer idMax;

    /**
     * 编码
     */
    private String optionCode;

    /**
     * 编码 列表
     */
    private List<String> optionCodeList;

    /**
     * 编码为空
     */
    private Boolean optionCodeIsEmpty;

    /**
     * 编码
     */
    private String optionCodeStartWith;

    /**
     * 编码
     */
    private String optionCodeContains;

    /**
     * 值
     */
    private String value;

    /**
     * 值 列表
     */
    private List<String> valueList;

    /**
     * 值为空
     */
    private Boolean valueIsEmpty;

    /**
     * 值
     */
    private String valueStartWith;

    /**
     * 值
     */
    private String valueContains;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 父ID 列表
     */
    private List<Integer> parentIdList;

    /**
     * 最小 父ID
     */
    private Integer parentIdMin;

    /**
     * 最大 父ID
     */
    private Integer parentIdMax;

    /**
     * 分组
     */
    private String source;

    public OptionCondition getCondition() {

        return getConditionExtension();
    }

    public OptionConditionExtension getConditionExtension() {

        OptionConditionExtension condition = new OptionConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setId(this.id);
        condition.setIdList(this.idList);
        condition.setIdMin(this.idMin);
        condition.setIdMax(this.idMax);
        condition.setOptionCode(this.optionCode);
        condition.setOptionCodeList(this.optionCodeList);
        condition.setOptionCodeIsEmpty(this.optionCodeIsEmpty);
        condition.setOptionCodeStartWith(this.optionCodeStartWith);
        condition.setOptionCodeContains(this.optionCodeContains);
        condition.setValue(this.value);
        condition.setValueList(this.valueList);
        condition.setValueIsEmpty(this.valueIsEmpty);
        condition.setValueStartWith(this.valueStartWith);
        condition.setValueContains(this.valueContains);
        condition.setParentId(this.parentId);
        condition.setParentIdList(this.parentIdList);
        condition.setParentIdMin(this.parentIdMin);
        condition.setParentIdMax(this.parentIdMax);
        condition.setSource(source);
        return condition;
    }
}
