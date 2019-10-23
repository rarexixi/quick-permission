package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RoleMenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.RoleMenuDetailVm;
import org.xi.quick.sys.vm.order.RoleMenuOrderVm;
import org.xi.quick.sys.vm.search.RoleMenuSearchVm;

import java.util.List;

/**
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface RoleMenuService extends BaseService<RoleMenuAddOrEditVm, RoleMenuDetailVm, RoleMenuOrderVm, RoleMenuSearchVm> {

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm);

    List<Integer> getMenuIdList(Integer roleId);

    List<Integer> getRoleIdList(Integer menuId);

    /**
     * 根据主键获取角色菜单详情
     *
     * @param roleId
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    RoleMenuDetailVm getDetail(Integer roleId, Integer menuId);
}
