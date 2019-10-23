package org.xi.quick.sys.models.entity;

import org.xi.quick.common.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class OptionEntity extends BaseEntity {

    /**
     * 枚举ID
     */
    private Integer id;

    /**
     * 编码
     */
    private String optionCode;

    /**
     * 值
     */
    private String value;

    /**
     * 展示文字
     */
    private String text;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 类型 (0分组,1选项)
     */
    private Integer type;

    /**
     * 是否系统内置 (0否,1是)
     */
    private Integer builtIn;

    /**
     * 扩展信息
     */
    private String ext;
}
