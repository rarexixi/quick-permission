package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.ConfigEntity;
import org.xi.quick.sys.models.entity.extension.ConfigEntityExtension;

import lombok.*;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConfigDetailVm extends BaseEntity {

    public ConfigDetailVm(ConfigEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        key = entity.getKey();
        value = entity.getValue();
        remark = entity.getRemark();
    }

    public ConfigDetailVm(ConfigEntity entity) {
        super(entity);

        if (entity == null) return;
        key = entity.getKey();
        value = entity.getValue();
        remark = entity.getRemark();
    }

    /**
     * 配置键
     */
    @ExcelCol("配置键")
    private String key;

    /**
     * 配置值
     */
    @ExcelCol("配置值")
    private String value;

    /**
     * 备注
     */
    @ExcelCol("备注")
    private String remark;
}
