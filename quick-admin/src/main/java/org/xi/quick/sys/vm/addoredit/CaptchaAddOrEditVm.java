package org.xi.quick.sys.vm.addoredit;

import org.xi.quick.common.model.BaseEntity;
import org.xi.quick.common.validation.DataAdd;
import org.xi.quick.common.validation.DataEdit;
import org.xi.quick.sys.models.entity.CaptchaEntity;

import lombok.*;

import javax.validation.constraints.*;
import java.util.*;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CaptchaAddOrEditVm extends BaseEntity {

    /**
     * 标识
     */
    @NotBlank(groups = {DataAdd.class}, message = "uuid (标识)不能为空")
    private String uuid;

    /**
     * 验证码
     */
    @NotBlank(groups = {DataAdd.class, DataEdit.class}, message = "code (验证码)不能为空")
    private String code;

    /**
     * 过期时间
     */
    @NotNull(groups = {DataAdd.class, DataEdit.class}, message = "expireAt (过期时间)不能为空")
    private Date expireAt;

    public CaptchaEntity getCaptchaEntity() {

        CaptchaEntity entity = new CaptchaEntity();
        entity.setUuid(this.uuid);
        entity.setCode(this.code);
        entity.setExpireAt(this.expireAt);
        super.setTargetEntity(entity);

        return entity;
    }

    public void setCaptchaEntity(CaptchaEntity entity) {

        if (entity == null) return;

        this.uuid = entity.getUuid();
        this.code = entity.getCode();
        this.expireAt = entity.getExpireAt();
        super.setCurrentEntity(entity);
    }
}
