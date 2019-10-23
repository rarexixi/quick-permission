package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.UserRoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.UserRoleDetailVm;
import org.xi.quick.sys.vm.order.UserRoleOrderVm;
import org.xi.quick.sys.vm.search.UserRoleSearchVm;

import java.util.List;

/**
 * 用户角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface UserRoleService extends BaseService<UserRoleAddOrEditVm, UserRoleDetailVm, UserRoleOrderVm, UserRoleSearchVm> {

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm);

    List<Integer> getRoleIdList(Integer userId);

    /**
     * 根据主键获取用户角色详情
     *
     * @param userId
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserRoleDetailVm getDetail(Integer userId, Integer roleId);
}
