package org.xi.quick.sys.service.impl;

import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.RolePermissionMapper;
import org.xi.quick.sys.models.condition.RolePermissionCondition;
import org.xi.quick.sys.models.condition.order.RolePermissionOrderCondition;
import org.xi.quick.sys.models.entity.RolePermissionEntity;
import org.xi.quick.sys.models.condition.extension.RolePermissionConditionExtension;
import org.xi.quick.sys.models.entity.extension.RolePermissionEntityExtension;
import org.xi.quick.sys.service.RolePermissionService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RolePermissionAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.RolePermissionDetailVm;
import org.xi.quick.sys.vm.order.RolePermissionOrderVm;
import org.xi.quick.sys.vm.search.RolePermissionSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("rolePermissionService")
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    public RolePermissionServiceImpl(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    private RolePermissionMapper rolePermissionMapper;

    /**
     * 添加角色权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(RolePermissionAddOrEditVm vm) {
        RolePermissionEntity entity = vm.getRolePermissionEntity();
        int count = rolePermissionMapper.insert(entity);
        vm.setRolePermissionEntity(entity);
        return count;
    }

    /**
     * 批量保存
     *
     * @param vm
     * @return
     */
    @Override
    public MtmRelationBatchResultVm batchSave(MtmRelationBatchSaveVm vm) {
        MtmRelationBatchResultVm result = new MtmRelationBatchResultVm();
        if (vm.getAddIdList() != null && !vm.getAddIdList().isEmpty()) {
            List<RolePermissionEntity> addList = vm.getAddIdList().stream().map(item -> new RolePermissionEntity(vm.getMainId(), item)).collect(Collectors.toList());
            result.setAdd(rolePermissionMapper.batchInsert(addList));
        }
        if (vm.getDelIdList() != null && !vm.getDelIdList().isEmpty()) {
            RolePermissionCondition delCondition = new RolePermissionCondition();
            delCondition.setRoleId(vm.getMainId());
            delCondition.setPermissionIdList(vm.getDelIdList());
            result.setAdd(rolePermissionMapper.deleteByCondition(delCondition));
        }
        return result;
    }

    @Override
    public List<Integer> getPermissionIdList(Integer roleId) {
        RolePermissionCondition condition = new RolePermissionCondition();
        condition.setRoleId(roleId);
        List<RolePermissionEntity> list = rolePermissionMapper.selectByCondition(condition, null);
        return list.stream().map(RolePermissionEntity::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getRoleIdList(Integer permissionId) {
        RolePermissionCondition condition = new RolePermissionCondition();
        condition.setPermissionId(permissionId);
        List<RolePermissionEntity> list = rolePermissionMapper.selectByCondition(condition, null);
        return list.stream().map(RolePermissionEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * 根据条件删除角色权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(RolePermissionSearchVm searchVm) {
        RolePermissionCondition condition = searchVm.getCondition();
        return rolePermissionMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件获取角色权限实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public RolePermissionDetailVm get(RolePermissionSearchVm searchVm) {
        RolePermissionCondition condition = searchVm.getCondition();
        RolePermissionEntity entity = rolePermissionMapper.getByCondition(condition);
        if (entity == null) return null;
        RolePermissionDetailVm detailVm = new RolePermissionDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键获取角色权限详情
     *
     * @param roleId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public RolePermissionDetailVm getDetail(Integer roleId, Integer permissionId) {
        RolePermissionEntityExtension entity = rolePermissionMapper.getByPk(roleId, permissionId);
        if (entity == null) return null;
        RolePermissionDetailVm vm = new RolePermissionDetailVm(entity);
        return vm;
    }

    /**
     * 获取角色权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionDetailVm> getList(RolePermissionSearchVm search) {
        if (search == null) return null;
        List<RolePermissionEntityExtension> list = rolePermissionMapper.getExList(search.getConditionExtension(), null);
        List<RolePermissionDetailVm> vmList = list.stream().map(RolePermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取角色权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionDetailVm> getList(OrderSearch<RolePermissionSearchVm, RolePermissionOrderVm> search) {
        if (search == null) return null;
        OrderSearch<RolePermissionConditionExtension, RolePermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<RolePermissionEntityExtension> list = rolePermissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<RolePermissionDetailVm> vmList = list.stream().map(RolePermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询角色权限
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<RolePermissionDetailVm> getPageInfo(OrderSearchPage<RolePermissionSearchVm, RolePermissionOrderVm> searchPage) {

        OrderSearch<RolePermissionConditionExtension, RolePermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<RolePermissionEntityExtension> list = rolePermissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<RolePermissionEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<RolePermissionDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, RolePermissionDetailVm::new);
        return pageInfoVo;
    }
}
