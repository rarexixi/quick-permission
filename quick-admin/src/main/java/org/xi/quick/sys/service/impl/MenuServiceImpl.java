package org.xi.quick.sys.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.xi.quick.common.utils.VoUtils;
import org.xi.quick.sys.mapper.MenuMapper;
import org.xi.quick.sys.models.condition.MenuCondition;
import org.xi.quick.sys.models.condition.order.MenuOrderCondition;
import org.xi.quick.sys.models.entity.MenuEntity;
import org.xi.quick.sys.models.condition.extension.MenuConditionExtension;
import org.xi.quick.sys.models.entity.extension.MenuEntityExtension;
import org.xi.quick.sys.service.MenuService;
import org.xi.quick.sys.vm.addoredit.MenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.order.MenuOrderVm;
import org.xi.quick.sys.vm.search.MenuSearchVm;

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
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    private MenuMapper menuMapper;

    /**
     * 添加菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int add(MenuAddOrEditVm vm) {
        MenuEntity entity = vm.getMenuEntity();
        int count = menuMapper.insert(entity);
        vm.setMenuEntity(entity);
        return count;
    }

    /**
     * 根据条件删除菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int delete(MenuSearchVm searchVm) {
        MenuCondition condition = searchVm.getCondition();
        return menuMapper.deleteByCondition(condition);
    }

    /**
     * 根据条件禁用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int disable(MenuSearchVm searchVm) {
        MenuCondition condition = searchVm.getCondition();
        MenuEntity entity = new MenuEntity();
        entity.setDeleted(1);
        return menuMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件启用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int enable(MenuSearchVm searchVm) {
        MenuCondition condition = searchVm.getCondition();
        MenuEntity entity = new MenuEntity();
        entity.setDeleted(0);
        return menuMapper.updateByCondition(entity, condition);
    }

    /**
     * 根据条件获取菜单实体
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public MenuDetailVm get(MenuSearchVm searchVm) {
        MenuCondition condition = searchVm.getCondition();
        MenuEntity entity = menuMapper.getByCondition(condition);
        if (entity == null) return null;
        MenuDetailVm detailVm = new MenuDetailVm(entity);
        return detailVm;
    }

    /**
     * 根据主键更新菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    public int update(MenuAddOrEditVm vm) {
        MenuCondition condition = new MenuCondition();
        condition.setMenuId(vm.getMenuId());
        return menuMapper.updateByCondition(vm.getMenuEntity(), condition);
    }

    /**
     * 根据主键获取菜单详情
     *
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Cacheable(value = "menu", key = "#menuId")
    @Override
    @Transactional(readOnly = true)
    public MenuDetailVm getDetail(Integer menuId) {
        MenuEntityExtension entity = menuMapper.getByPk(menuId);
        if (entity == null) return null;
        MenuDetailVm vm = new MenuDetailVm(entity);
        return vm;
    }

    /**
     * 获取菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuDetailVm> getList(MenuSearchVm search) {
        if (search == null) return null;
        List<MenuEntityExtension> list = menuMapper.getExList(search.getConditionExtension(), null);
        List<MenuDetailVm> vmList = list.stream().map(MenuDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 获取菜单列表
     *
     * @param search
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public List<MenuDetailVm> getList(OrderSearch<MenuSearchVm, MenuOrderVm> search) {
        if (search == null) return null;
        OrderSearch<MenuConditionExtension, MenuOrderCondition> orderSearch = VoUtils.getOrderSearch(search);
        List<MenuEntityExtension> list = menuMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        List<MenuDetailVm> vmList = list.stream().map(MenuDetailVm::new).collect(Collectors.toList());
        return vmList;
    }

    /**
     * 分页查询菜单
     *
     * @param searchPage
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    @Override
    @Transactional(readOnly = true)
    public PageInfoVo<MenuDetailVm> getPageInfo(OrderSearchPage<MenuSearchVm, MenuOrderVm> searchPage) {

        OrderSearch<MenuConditionExtension, MenuOrderCondition> orderSearch = VoUtils.getOrderSearch(searchPage);

        PageHelper.startPage(searchPage.getPageIndex(), searchPage.getPageSize());
        List<MenuEntityExtension> list = menuMapper.getExList(orderSearch.getCondition(), orderSearch.getOrder());
        PageInfo<MenuEntityExtension> pageInfo = new PageInfo<>(list);

        PageInfoVo<MenuDetailVm> pageInfoVo = VoUtils.getPageInfoVo(pageInfo, MenuDetailVm::new);
        return pageInfoVo;
    }
}
