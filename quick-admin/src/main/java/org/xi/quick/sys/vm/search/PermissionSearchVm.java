package org.xi.quick.sys.vm.search;

import org.xi.quick.common.model.SearchVm;
import org.xi.quick.sys.models.condition.PermissionCondition;
import org.xi.quick.sys.models.condition.extension.PermissionConditionExtension;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Getter
@Setter
@ToString
public class PermissionSearchVm implements SearchVm {

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 权限ID
     */
    private Integer permissionId;

    /**
     * 权限ID 列表
     */
    private List<Integer> permissionIdList;

    /**
     * 最小 权限ID
     */
    private Integer permissionIdMin;

    /**
     * 最大 权限ID
     */
    private Integer permissionIdMax;

    public PermissionCondition getCondition() {

        return getConditionExtension();
    }

    public PermissionConditionExtension getConditionExtension() {

        PermissionConditionExtension condition = new PermissionConditionExtension();
        condition.setDeleted(this.deleted);
        condition.setPermissionId(this.permissionId);
        condition.setPermissionIdList(this.permissionIdList);
        condition.setPermissionIdMin(this.permissionIdMin);
        condition.setPermissionIdMax(this.permissionIdMax);
        return condition;
    }
}
