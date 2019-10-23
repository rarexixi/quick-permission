package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.validation.*;
import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.sys.models.entity.OptionEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 系统选项
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OptionAddOrEditVm extends BaseEntity {

    /**
     * 枚举ID
     */
    @NotNull(groups = {DataEdit.class}, message = "id (枚举ID)不能为空")
    private Integer id;

    /**
     * 编码
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "optionCode (编码)不能为空")
    private String optionCode;

    /**
     * 值
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "value (值)不能为空")
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

    public OptionEntity getOptionEntity() {

        OptionEntity entity = new OptionEntity();
        entity.setId(this.id);
        entity.setOptionCode(this.optionCode);
        entity.setValue(this.value);
        entity.setText(this.text);
        entity.setSort(this.sort);
        entity.setParentId(this.parentId);
        entity.setType(this.type);
        entity.setBuiltIn(this.builtIn);
        entity.setExt(this.ext);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setOptionEntity(OptionEntity entity) {

        if (entity == null) return;

        this.id = entity.getId();
        this.optionCode = entity.getOptionCode();
        this.value = entity.getValue();
        this.text = entity.getText();
        this.sort = entity.getSort();
        this.parentId = entity.getParentId();
        this.type = entity.getType();
        this.builtIn = entity.getBuiltIn();
        this.ext = entity.getExt();
        super.setCurrentEntity(entity);
    }
}
