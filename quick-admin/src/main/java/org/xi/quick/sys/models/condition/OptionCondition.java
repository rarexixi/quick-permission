package org.xi.quick.sys.models.condition;

import org.xi.quick.common.model.BaseCondition;

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
public class OptionCondition extends BaseCondition {

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
     * 展示文字
     */
    private String text;

    /**
     * 展示文字 列表
     */
    private List<String> textList;

    /**
     * 展示文字为空
     */
    private Boolean textIsEmpty;

    /**
     * 展示文字
     */
    private String textStartWith;

    /**
     * 展示文字
     */
    private String textContains;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 排序 列表
     */
    private List<Integer> sortList;

    /**
     * 最小 排序
     */
    private Integer sortMin;

    /**
     * 最大 排序
     */
    private Integer sortMax;

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
     * 类型 (0分组,1选项)
     */
    private Integer type;

    /**
     * 类型 列表
     */
    private List<Integer> typeList;

    /**
     * 最小 类型
     */
    private Integer typeMin;

    /**
     * 最大 类型
     */
    private Integer typeMax;

    /**
     * 是否系统内置 (0否,1是)
     */
    private Integer builtIn;

    /**
     * 是否系统内置 列表
     */
    private List<Integer> builtInList;

    /**
     * 最小 是否系统内置
     */
    private Integer builtInMin;

    /**
     * 最大 是否系统内置
     */
    private Integer builtInMax;
}
