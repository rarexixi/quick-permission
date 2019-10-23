package org.xi.quick.sys.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.RoleMapper;
import org.xi.quick.sys.models.condition.RoleCondition;
import org.xi.quick.sys.models.condition.order.RoleOrderCondition;
import org.xi.quick.sys.models.entity.RoleEntity;
import org.xi.quick.sys.models.condition.extension.RoleConditionExtension;
import org.xi.quick.sys.models.entity.extension.RoleEntityExtension;
import org.xi.quick.sys.service.RoleService;
import org.xi.quick.sys.vm.addoredit.RoleAddOrEditVm;
import org.xi.quick.sys.vm.detail.RoleDetailVm;
import org.xi.quick.sys.vm.order.RoleOrderVm;
import org.xi.quick.sys.vm.search.RoleSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xi.quick.common.model.OrderSearch;
import org.xi.quick.common.model.OrderSearchPage;
import org.xi.quick.common.model.PageInfoVo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    private RoleMapper roleMapper;

    /**
     * 添加角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(RoleAddOrEditVm vm) {
        RoleEntity entity = vm.getRoleEntity();
        int count = roleMapper.insert(entity);
        vm.setRoleEntity(entity);
        return count;
    }

    /**
     * 根据条件删除角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(RoleSearchVm searchVm) {
        RoleCondition condition = searchVm.getCondition();
        return roleMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(RoleSearchVm searchVm) {
        RoleCondition condition = searchVm.getCondition();
        RoleEntity entity = new RoleEntity();
        entity.setDeleted(1);
        return roleMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用角色
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(RoleSearchVm searchVm) {
        RoleCondition condition = searchVm.getCondition();
        RoleEntity entity = new RoleEntity();
        entity.setDeleted(0);
        return roleMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取角色实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public RoleDetailVm get(RoleSearchVm searchVm) {
        RoleCondition condition = searchVm.getCondition();
        RoleEntity entity = roleMapper.getByCondition(condition);
        if (entity == null) return null;
        RoleDetailVm detailVm = new RoleDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新角色
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(RoleAddOrEditVm vm) {
        RoleCondition condition = new RoleCondition();
        condition.setRoleId(vm.getRoleId());
        return roleMapper.updateByCondition(vm.getRoleEntity(), condition);
    }

    /**
     * 根据主键获取角色详情
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Cacheable(value = "role", key = "#roleId")
    @Override
    @Transactional(readOnly = true)
    public RoleDetailVm getDetail(Integer roleId) {
        RoleEntityExtension entity = roleMapper.getByPk(roleId);
        if (entity == null) return null;
        RoleDetailVm vm = new RoleDetailVm(entity);
        return vm;
    }

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<String> getRolePermissions(Integer roleId) {
        return roleMapper.getPermissionsByRoleId(roleId);
    }

    /**
     * 根据角色ID列表获取权限列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<String> getRolesPermissions(List<Integer> roleIds) {
        return roleMapper.getPermissionsByRoleIds(roleIds);
    }

    /**
     * 根据角色ID获取权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<Integer> getRolePermissionIds(Integer roleId) {
        return roleMapper.getPermissionIdsByRoleId(roleId);
    }

    /**
     * 根据角色ID列表获取权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<Integer> getRolesPermissionIds(List<Integer> roleIds) {
        return roleMapper.getPermissionIdsByRoleIds(roleIds);
    }

    /**
     * 根据角色ID获取菜单权限ID列表
     *
     * @param roleId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<Integer> getMenuRolePermissionIds(Integer roleId) {
        return roleMapper.getMenuPermissionIdsByRoleId(roleId);
    }

    /**
     * 根据角色ID列表获取菜单权限ID列表
     *
     * @param roleIds
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public Set<Integer> getMenuRolesPermissionIds(List<Integer> roleIds) {
        return roleMapper.getMenuPermissionIdsByRoleIds(roleIds);
    }

    /**
     * 获取角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleDetailVm> getList(RoleSearchVm search) {
        if (search == null) return null;
        List<RoleEntityExtension> list = roleMapper.getExList(search.getConditionExtension(), null);
        List<RoleDetailVm> vmList = list.stream().map(RoleDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取角色列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleDetailVm> getList(OrderSearch<RoleSearchVm, RoleOrderVm> search) {
        if (search == null) return null;
        OrderSearch<RoleConditionExtension, RoleOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<RoleEntityExtension> list = roleMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<RoleDetailVm> vmList = list.stream().map(RoleDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询角色
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<RoleDetailVm> getPageInfo(OrderSearchPage<RoleSearchVm, RoleOrderVm> searchPage) {

        OrderSearch<RoleConditionExtension, RoleOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<RoleEntityExtension> list = roleMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<RoleEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<RoleDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, RoleDetailVm::new);
        return pageInfoVo;
    }
}
