package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.MenuAddOrEditVm;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.order.MenuOrderVm;
import org.xi.quick.sys.vm.search.MenuSearchVm;

/**
 * 菜单
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface MenuService extends BaseService<MenuAddOrEditVm, MenuDetailVm, MenuOrderVm, MenuSearchVm> {

    /**
     * 根据条件禁用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(MenuSearchVm searchVm);

    /**
     * 根据条件启用菜单
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(MenuSearchVm searchVm);

    /**
     * 根据主键更新菜单
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(MenuAddOrEditVm vm);

    /**
     * 根据主键获取菜单详情
     *
     * @param menuId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    MenuDetailVm getDetail(Integer menuId);
}
