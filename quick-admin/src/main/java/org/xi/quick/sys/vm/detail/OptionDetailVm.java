package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.OptionEntity;
import org.xi.quick.sys.models.entity.extension.OptionEntityExtension;

import lombok.*;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OptionDetailVm extends BaseEntity {

    public OptionDetailVm(OptionEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        id = entity.getId();
        optionCode = entity.getOptionCode();
        value = entity.getValue();
        text = entity.getText();
        sort = entity.getSort();
        parentId = entity.getParentId();
        type = entity.getType();
        typeText = entity.getTypeText();
        builtIn = entity.getBuiltIn();
        ext = entity.getExt();
    }

    public OptionDetailVm(OptionEntity entity) {
        super(entity);

        if (entity == null) return;
        id = entity.getId();
        optionCode = entity.getOptionCode();
        value = entity.getValue();
        text = entity.getText();
        sort = entity.getSort();
        parentId = entity.getParentId();
        type = entity.getType();
        builtIn = entity.getBuiltIn();
        ext = entity.getExt();
    }

    /**
     * 枚举ID
     */
    @ExcelCol("枚举ID")
    private Integer id;

    /**
     * 编码
     */
    @ExcelCol("编码")
    private String optionCode;

    /**
     * 值
     */
    @ExcelCol("值")
    private String value;

    /**
     * 展示文字
     */
    @ExcelCol("展示文字")
    private String text;

    /**
     * 排序
     */
    @ExcelCol("排序")
    private Integer sort;

    /**
     * 父ID
     */
    @ExcelCol("父ID")
    private Integer parentId;

    /**
     * 类型 (0分组,1选项)
     */
    @ExcelCol("类型")
    private Integer type;

    private String typeText;

    /**
     * 是否系统内置 (0否,1是)
     */
    @ExcelCol("是否系统内置")
    private Integer builtIn;

    /**
     * 扩展信息
     */
    @ExcelCol("扩展信息")
    private String ext;
}
