package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.PermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.PermissionDetailVm;
import org.xi.quick.sys.vm.order.PermissionOrderVm;
import org.xi.quick.sys.vm.search.PermissionSearchVm;

/**
 * 权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface PermissionService extends BaseService<PermissionAddOrEditVm, PermissionDetailVm, PermissionOrderVm, PermissionSearchVm> {

    /**
     * 根据条件禁用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(PermissionSearchVm searchVm);

    /**
     * 根据条件启用权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(PermissionSearchVm searchVm);

    /**
     * 根据主键更新权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(PermissionAddOrEditVm vm);

    /**
     * 根据主键获取权限详情
     *
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    PermissionDetailVm getDetail(Integer permissionId);
}
