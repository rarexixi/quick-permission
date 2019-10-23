package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.sys.models.entity.ConfigEntity;

import lombok.*;

import javax.validation.constraints.*;

/**
 * 系统配置
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConfigAddOrEditVm extends BaseEntity {

    /**
     * 配置键
     */
    @NotBlank(groups = {DataAdd.class}, message = "key (配置键)不能为空")
    private String key;

    /**
     * 配置值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    public ConfigEntity getConfigEntity() {

        ConfigEntity entity = new ConfigEntity();
        entity.setKey(this.key);
        entity.setValue(this.value);
        entity.setRemark(this.remark);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setConfigEntity(ConfigEntity entity) {

        if (entity == null) return;

        this.key = entity.getKey();
        this.value = entity.getValue();
        this.remark = entity.getRemark();
        super.setCurrentEntity(entity);
    }
}
