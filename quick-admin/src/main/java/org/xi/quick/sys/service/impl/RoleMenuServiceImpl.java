package org.xi.quick.sys.service.impl;

import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.RoleMenuMapper;
import org.xi.quick.sys.models.condition.RoleMenuCondition;
import org.xi.quick.sys.models.condition.order.RoleMenuOrderCondition;
import org.xi.quick.sys.models.entity.RoleMenuEntity;
import org.xi.quick.sys.models.condition.extension.RoleMenuConditionExtension;
import org.xi.quick.sys.models.entity.extension.RoleMenuEntityExtension;
import org.xi.quick.sys.service.RoleMenuService;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.addoredit.RoleMenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.detail.RoleMenuDetailVm;
import org.xi.quick.sys.vm.order.RoleMenuOrderVm;
import org.xi.quick.sys.vm.search.RoleMenuSearchVm;

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
 * 角色菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    public RoleMenuServiceImpl(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    private RoleMenuMapper roleMenuMapper;

    /**
     * 添加角色菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(RoleMenuAddOrEditVm vm) {
        RoleMenuEntity entity = vm.getRoleMenuEntity();
        int count = roleMenuMapper.insert(entity);
        vm.setRoleMenuEntity(entity);
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
            List<RoleMenuEntity> addList = vm.getAddIdList().stream().map(item -> new RoleMenuEntity(vm.getMainId(), item)).collect(Collectors.toList());
            result.setAdd(roleMenuMapper.batchInsert(addList));
        }
        if (vm.getDelIdList() != null && !vm.getDelIdList().isEmpty()) {
            RoleMenuCondition delCondition = new RoleMenuCondition();
            delCondition.setRoleId(vm.getMainId());
            delCondition.setMenuIdList(vm.getDelIdList());
            result.setAdd(roleMenuMapper.deleteByCondition(delCondition));
        }
        return result;
    }

    @Override
    public List<Integer> getMenuIdList(Integer roleId) {
        RoleMenuCondition condition = new RoleMenuCondition();
        condition.setRoleId(roleId);
        List<RoleMenuEntity> list = roleMenuMapper.selectByCondition(condition, null);
        return list.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getRoleIdList(Integer menuId) {
        RoleMenuCondition condition = new RoleMenuCondition();
        condition.setMenuId(menuId);
        List<RoleMenuEntity> list = roleMenuMapper.selectByCondition(condition, null);
        return list.stream().map(RoleMenuEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * 根据条件删除角色菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(RoleMenuSearchVm searchVm) {
        RoleMenuCondition condition = searchVm.getCondition();
        return roleMenuMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件获取角色菜单实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public RoleMenuDetailVm get(RoleMenuSearchVm searchVm) {
        RoleMenuCondition condition = searchVm.getCondition();
        RoleMenuEntity entity = roleMenuMapper.getByCondition(condition);
        if (entity == null) return null;
        RoleMenuDetailVm detailVm = new RoleMenuDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键获取角色菜单详情
     *
     * @param roleId
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public RoleMenuDetailVm getDetail(Integer roleId, Integer menuId) {
        RoleMenuEntityExtension entity = roleMenuMapper.getByPk(roleId, menuId);
        if (entity == null) return null;
        RoleMenuDetailVm vm = new RoleMenuDetailVm(entity);
        return vm;
    }

    /**
     * 获取角色菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleMenuDetailVm> getList(RoleMenuSearchVm search) {
        if (search == null) return null;
        List<RoleMenuEntityExtension> list = roleMenuMapper.getExList(search.getConditionExtension(), null);
        List<RoleMenuDetailVm> vmList = list.stream().map(RoleMenuDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取角色菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<RoleMenuDetailVm> getList(OrderSearch<RoleMenuSearchVm, RoleMenuOrderVm> search) {
        if (search == null) return null;
        OrderSearch<RoleMenuConditionExtension, RoleMenuOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<RoleMenuEntityExtension> list = roleMenuMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<RoleMenuDetailVm> vmList = list.stream().map(RoleMenuDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询角色菜单
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<RoleMenuDetailVm> getPageInfo(OrderSearchPage<RoleMenuSearchVm, RoleMenuOrderVm> searchPage) {

        OrderSearch<RoleMenuConditionExtension, RoleMenuOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<RoleMenuEntityExtension> list = roleMenuMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<RoleMenuEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<RoleMenuDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, RoleMenuDetailVm::new);
        return pageInfoVo;
    }
}
