package org.xi.quick.sys.service;

import org.xi.quick.common.service.BaseService;

import org.xi.quick.sys.vm.addoredit.UserAddOrEditVm;
import org.xi.quick.sys.vm.detail.MenuDetailVm;
import org.xi.quick.sys.vm.detail.UserDetailVm;
import org.xi.quick.sys.vm.order.UserOrderVm;
import org.xi.quick.sys.vm.search.UserSearchVm;

import java.util.List;
import java.util.Set;

/**
 * 系统用户
 *
 * @author 郗世豪（rarexixi@sina.com） All Rights Reserved.
 */
public interface UserService extends BaseService<UserAddOrEditVm, UserDetailVm, UserOrderVm, UserSearchVm> {

    /**
     * 根据条件禁用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int disable(UserSearchVm searchVm);

    /**
     * 根据条件启用系统用户
     *
     * @param searchVm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int enable(UserSearchVm searchVm);

    /**
     * 根据主键更新系统用户
     *
     * @param vm
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int update(UserAddOrEditVm vm);

    /**
     * 根据主键更新密码
     *
     * @param userId
     * @param password
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    int setPassword(Integer userId, String password);

    /**
     * 根据主键获取系统用户详情
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserDetailVm getDetail(Integer userId);

    /**
     * 根据系统用户详情
     *
     * @param account
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    UserDetailVm getUserAccount(String account);

    /**
     * 根据主键获取系统用户角色
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getUserRoles(Integer userId);

    /**
     * 根据主键获取系统用户权限
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    Set<String> getUserPermissions(Integer userId);

    /**
     * 根据主键获取系统用户导航菜单
     *
     * @param userId
     * @return
     * @author 郗世豪（rarexixi@sina.com）
     */
    List<MenuDetailVm> getUserNavMenus(Integer userId);
}
