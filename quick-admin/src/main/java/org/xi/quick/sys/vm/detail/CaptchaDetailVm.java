package org.xi.quick.sys.vm.detail;

import org.xi.quick.common.annotation.ExcelCol;
import org.xi.quick.common.model.BaseEntity;

import org.xi.quick.sys.models.entity.CaptchaEntity;
import org.xi.quick.sys.models.entity.extension.CaptchaEntityExtension;

import lombok.*;

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
public class CaptchaDetailVm extends BaseEntity {

    public CaptchaDetailVm(CaptchaEntityExtension entity) {
        super(entity);

        if (entity == null) return;
        uuid = entity.getUuid();
        code = entity.getCode();
        expireAt = entity.getExpireAt();
    }

    public CaptchaDetailVm(CaptchaEntity entity) {
        super(entity);

        if (entity == null) return;
        uuid = entity.getUuid();
        code = entity.getCode();
        expireAt = entity.getExpireAt();
    }

    /**
     * 标识
     */
    @ExcelCol("标识")
    private String uuid;

    /**
     * 验证码
     */
    @ExcelCol("验证码")
    private String code;

    /**
     * 过期时间
     */
    @ExcelCol(value = "过期时间", formatter = "yyyy-MM-dd HH:mm")
    private Date expireAt;
}
