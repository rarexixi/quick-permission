package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.CaptchaCondition;
import org.xi.quick.sys.models.condition.extension.CaptchaConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 系统验证码
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class CaptchaSearchVm implements SearchVm {

    /**
     * 标识
     */
    private String uuid;

    /**
     * 标识 列表
     */
    private List<String> uuidList;

    /**
     * 标识为空
     */
    private Boolean uuidIsEmpty;

    /**
     * 标识
     */
    private String uuidStartWith;

    /**
     * 标识
     */
    private String uuidContains;

    public CaptchaCondition getCondition() {

        return getConditionExtension();
    }

    public CaptchaConditionExtension getConditionExtension() {

        CaptchaConditionExtension condition = new CaptchaConditionExtension();
        condition.setUuid(this.uuid);
        condition.setUuidList(this.uuidList);
        condition.setUuidIsEmpty(this.uuidIsEmpty);
        condition.setUuidStartWith(this.uuidStartWith);
        condition.setUuidContains(this.uuidContains);
        return condition;
    }
}
