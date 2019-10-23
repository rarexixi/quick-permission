package org.xi.quick.sys.service.impl;

import org.xi.quick.common.model.*;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.MenuPermissionMapper;
import org.xi.quick.sys.models.condition.MenuPermissionCondition;
import org.xi.quick.sys.models.condition.order.MenuPermissionOrderCondition;
import org.xi.quick.sys.models.entity.MenuPermissionEntity;
import org.xi.quick.sys.models.condition.extension.MenuPermissionConditionExtension;
import org.xi.quick.sys.models.entity.extension.MenuPermissionEntityExtension;
import org.xi.quick.sys.service.MenuPermissionService;
import org.xi.quick.sys.vm.addoredit.MenuPermissionAddOrEditVm;
import org.xi.quick.sys.vm.addoredit.MtmRelationBatchSaveVm;
import org.xi.quick.sys.vm.detail.MenuPermissionDetailVm;
import org.xi.quick.sys.vm.detail.MtmRelationBatchResultVm;
import org.xi.quick.sys.vm.order.MenuPermissionOrderVm;
import org.xi.quick.sys.vm.search.MenuPermissionSearchVm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单权限
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("menuPermissionService")
@Transactional
public class MenuPermissionServiceImpl implements MenuPermissionService {

    @Autowired
    public MenuPermissionServiceImpl(MenuPermissionMapper menuPermissionMapper) {
        this.menuPermissionMapper = menuPermissionMapper;
    }

    private MenuPermissionMapper menuPermissionMapper;

    /**
     * 添加菜单权限
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(MenuPermissionAddOrEditVm vm) {
        MenuPermissionEntity entity = vm.getMenuPermissionEntity();
        int count = menuPermissionMapper.insert(entity);
        vm.setMenuPermissionEntity(entity);
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
            List<MenuPermissionEntity> addList = vm.getAddIdList().stream().map(item -> new MenuPermissionEntity(vm.getMainId(), item)).collect(Collectors.toList());
            result.setAdd(menuPermissionMapper.batchInsert(addList));
        }
        if (vm.getDelIdList() != null && !vm.getDelIdList().isEmpty()) {
            MenuPermissionCondition delCondition = new MenuPermissionCondition();
            delCondition.setMenuId(vm.getMainId());
            delCondition.setPermissionIdList(vm.getDelIdList());
            result.setAdd(menuPermissionMapper.deleteByCondition(delCondition));
        }
        return result;
    }

    @Override
    public List<Integer> getPermissionIdList(Integer menuId) {
        MenuPermissionCondition condition = new MenuPermissionCondition();
        condition.setMenuId(menuId);
        List<MenuPermissionEntity> list = menuPermissionMapper.selectByCondition(condition, null);
        return list.stream().map(MenuPermissionEntity::getPermissionId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getMenuIdList(Integer permissionId) {
        MenuPermissionCondition condition = new MenuPermissionCondition();
        condition.setPermissionId(permissionId);
        List<MenuPermissionEntity> list = menuPermissionMapper.selectByCondition(condition, null);
        return list.stream().map(MenuPermissionEntity::getMenuId).collect(Collectors.toList());
    }

    /**
     * 根据条件删除菜单权限
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(MenuPermissionSearchVm searchVm) {
        MenuPermissionCondition condition = searchVm.getCondition();
        return menuPermissionMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件获取菜单权限实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public MenuPermissionDetailVm get(MenuPermissionSearchVm searchVm) {
        MenuPermissionCondition condition = searchVm.getCondition();
        MenuPermissionEntity entity = menuPermissionMapper.getByCondition(condition);
        if (entity == null) return null;
        MenuPermissionDetailVm detailVm = new MenuPermissionDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键获取菜单权限详情
     *
     * @param menuId
     * @param permissionId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public MenuPermissionDetailVm getDetail(Integer menuId, Integer permissionId) {
        MenuPermissionEntityExtension entity = menuPermissionMapper.getByPk(menuId, permissionId);
        if (entity == null) return null;
        MenuPermissionDetailVm vm = new MenuPermissionDetailVm(entity);
        return vm;
    }

    /**
     * 获取菜单权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuPermissionDetailVm> getList(MenuPermissionSearchVm search) {
        if (search == null) return null;
        List<MenuPermissionEntityExtension> list = menuPermissionMapper.getExList(search.getConditionExtension(), null);
        List<MenuPermissionDetailVm> vmList = list.stream().map(MenuPermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取菜单权限列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuPermissionDetailVm> getList(OrderSearch<MenuPermissionSearchVm, MenuPermissionOrderVm> search) {
        if (search == null) return null;
        OrderSearch<MenuPermissionConditionExtension, MenuPermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<MenuPermissionEntityExtension> list = menuPermissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<MenuPermissionDetailVm> vmList = list.stream().map(MenuPermissionDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询菜单权限
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<MenuPermissionDetailVm> getPageInfo(OrderSearchPage<MenuPermissionSearchVm, MenuPermissionOrderVm> searchPage) {

        OrderSearch<MenuPermissionConditionExtension, MenuPermissionOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<MenuPermissionEntityExtension> list = menuPermissionMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<MenuPermissionEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<MenuPermissionDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, MenuPermissionDetailVm::new);
        return pageInfoVo;
    }
}
