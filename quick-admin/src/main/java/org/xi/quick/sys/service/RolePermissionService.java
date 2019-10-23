package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RolePermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.RolePermissionDetailVm;
import org.xi.quick.sys.vm.order.RolePermissionOrderVm;
import org.xi.quick.sys.vm.search.RolePermissionSearchVm;

import java.util.List;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface RolePermissionService extends BaseService<RolePermissionAddOrEditVm, RolePermissionDetailVm, RolePermissionOrderVm, RolePermissionSearchVm> {

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm);

    List<Integer> getPermissionIdList(Integer roleId);

    List<Integer> getRoleIdList(Integer permissionId);

    /**
     * 根据主键获取角色权限详情
     *
     * @param roleId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RolePermissionDetailVm getDetail(Integer roleId, Integer permissionId);
}
