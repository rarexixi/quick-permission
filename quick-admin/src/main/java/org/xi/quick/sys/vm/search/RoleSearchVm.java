package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.RoleCondition;
import org.xi.quick.sys.models.condition.extension.RoleConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class RoleSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色ID 列表
     */
    private List<Integer> roleIdList;

    /**
     * 最小 角色ID
     */
    private Integer roleIdMin;

    /**
     * 最大 角色ID
     */
    private Integer roleIdMax;

    public RoleCondition getCondition() {

        return getConditionExtension();
    }

    public RoleConditionExtension getConditionExtension() {

        RoleConditionExtension condition = new RoleConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setRoleId(this.roleId);
        condition.setRoleIdList(this.roleIdList);
        condition.setRoleIdMin(this.roleIdMin);
        condition.setRoleIdMax(this.roleIdMax);
        return condition;
    }
}
