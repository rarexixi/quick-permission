package org.xi.quick.sys.models.condition.extension;

import org.xi.quick.sys.models.condition.UserCondition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class UserConditionExtension extends UserCondition {

    /**
     * 用户角色
     */
    private Integer roleId;
}
