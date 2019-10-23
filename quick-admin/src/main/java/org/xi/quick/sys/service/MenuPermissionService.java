package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.MenuPermissionAddOrEditVm;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.detail.MenuPermissionDetailVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.order.MenuPermissionOrderVm;
import org.xi.quick.sys.vm.search.MenuPermissionSearchVm;

import java.util.List;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface MenuPermissionService extends BaseService<MenuPermissionAddOrEditVm, MenuPermissionDetailVm, MenuPermissionOrderVm, MenuPermissionSearchVm> {

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm);

    List<Integer> getPermissionIdList(Integer menuId);

    List<Integer> getMenuIdList(Integer permissionId);

    /**
     * 根据主键获取菜单权限详情
     *
     * @param menuId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    MenuPermissionDetailVm getDetail(Integer menuId, Integer permissionId);
}
